package com.xsg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsg.entity.dto.IESearchDTO;
import com.xsg.entity.dto.IESearchExcelDTO;
import com.xsg.mapper.ImportExportInfoMapper;
import com.xsg.service.DownLoadService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: 下载中心业务实现
 */
@Slf4j
@Service
public class DownLoadServiceImpl implements DownLoadService {

    @Setter(onMethod_ = {@Autowired})
    private ImportExportInfoMapper importExportInfoMapper;

    @Override
    public void createDownloadSingleTask(String recordId) {
        //接口限流
        //围绕几个点
        //海量数据 不能一次查出来到处 需要分页查询
        //File file = pageSearchDataSingle(recordId);
        File file = pageSearchDataDouble(recordId);
        log.info("data:{}", JSON.toJSONString(file.getAbsolutePath()));
        //考虑到RT响应的时间 需要借助多线程处理数据
        // 多线程的结果依次写入到excel里去 借助EasyExcel
        // excel的内容存储 磁盘/oss/对象存储minio
    }

    /**
     * 单线程 分页查询数据 15w数据耗时215s
     */
    public File pageSearchDataSingle(String fileId) {
        // 创建excel
        File file = FileUtil.createTempFile(fileId, ".xlsx", true);
        ExcelWriter excelWriter = EasyExcel.write(file, IESearchExcelDTO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 开始写
        int pageNum = 1;
        List<IESearchDTO> resultList = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        while (true) {
            Page<Object> page = Page.of(pageNum, 300);
            log.info("开始分页查询");
            IPage<IESearchDTO> searchPage = importExportInfoMapper.searchListPage(page);
            List<IESearchDTO> searchList = searchPage.getRecords();
            if (CollectionUtils.isNotEmpty(searchList)) {
                resultList.addAll(searchList);
            }
            // 1500条写一次吧
            if (resultList.size() >= 1500) {
                List<IESearchExcelDTO> excelList = BeanUtil.copyToList(resultList, IESearchExcelDTO.class);
                log.info("开始数据写入");
                excelWriter.write(excelList, writeSheet);
                // 写完就清空resultList
                log.info("开始数据清空");
                resultList.clear();
            }
            pageNum++;
            if (CollectionUtils.isEmpty(searchList)) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        log.info("task cost time:{}", (endTime - beginTime));
        excelWriter.finish();
        excelWriter.close();
        return file;
    }

    /**
     * 尝试多线程实现效率的提升
     *
     * @param recordId 记录id
     * @return excel file
     */
    public File pageSearchDataDouble(String recordId) {
        // 创建excel
        File file = FileUtil.createTempFile(recordId, ".xlsx", true);
        ExcelWriter excelWriter = EasyExcel.write(file, IESearchExcelDTO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 线程安全的结果列表
        List<IESearchDTO> resultList = Collections.synchronizedList(new ArrayList<>());
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        // 用于存储future
        List<Future<?>> futureList = new ArrayList<>();
        // 分页查询任务
        final AtomicInteger pageNum = new AtomicInteger(1);
        long beginTime = System.currentTimeMillis();
        do {
            // 提交分页查询任务
            Future<?> future = executorService.submit(() -> {
                // 获取当前页码
                int currentPageNum = pageNum.get();
                Page<Object> page = Page.of(currentPageNum, 300);
                log.info("开始分页查询，页码：{}", pageNum);
                IPage<IESearchDTO> searchPage = importExportInfoMapper.searchListPage(page);
                List<IESearchDTO> searchList = searchPage.getRecords();
                if (CollectionUtils.isNotEmpty(searchList)) {
                    resultList.addAll(searchList);
                }
            });
            futureList.add(future);
            pageNum.incrementAndGet();
            // 如果当前页没有数据，退出循环
        } while (pageNum.get() <= 1  || !CollectionUtils.isEmpty(importExportInfoMapper.searchListPage(Page.of(pageNum.get() , 300)).getRecords()));

        // 等待所有查询任务完成
        for (Future<?> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("分页查询任务执行失败", e);
                Thread.currentThread().interrupt();
            }
        }

        // 写入Excel
        int writeCount = 0;
        while (writeCount < resultList.size()) {
            int start = writeCount;
            int end = Math.min(writeCount + 1500, resultList.size());
            List<IESearchDTO> subList = resultList.subList(start, end);
            List<IESearchExcelDTO> excelList = BeanUtil.copyToList(subList, IESearchExcelDTO.class);
            log.info("开始写数据了");
            excelWriter.write(excelList, writeSheet);
            writeCount += 1500;
        }

        long endTime = System.currentTimeMillis();
        log.info("task cost time:{}", (endTime - beginTime));
        excelWriter.finish();
        excelWriter.close();
        executorService.shutdown();
        return file;
    }
}
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
       File file = pageSearchData(recordId);
       log.info("data:" + JSON.toJSONString(file.getAbsolutePath()));
       //考虑到RT响应的时间 需要借助多线程处理数据
       // 多线程的结果依次写入到excel里去 借助EasyExcel
       // excel的内容存储 磁盘/oss/对象存储minio
    }

    /**
     * 分页查询数据
     *
     * @return
     */
    public File pageSearchData(String fileId) {
        // 创建excel
        File file = FileUtil.createTempFile(fileId, ".xlsx", true);
        ExcelWriter excelWriter = EasyExcel.write(file, IESearchExcelDTO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 开始写
        int pageNum = 1;
        List<IESearchDTO> resultList = new ArrayList<>();
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
        return file;
    }
}

package com.xsg.service;/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: 下载中心业务实现
 */
public interface DownLoadService {

    /**
     * 单线程导出数据
     * @param uuid
     */
    void createDownloadSingleTask(String uuid);
}

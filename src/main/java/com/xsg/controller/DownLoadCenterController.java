package com.xsg.controller;

import com.xsg.manager.DownLoadManager;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: 下载中心
 */
@RestController
@RequestMapping("/center")
public class DownLoadCenterController {

    @Setter(onMethod_ = {@Autowired})
    private DownLoadManager downLoadManager;

    /**
     * 场景
     * 对应海量数据 百万级
     * @return
     */
    @GetMapping("/create/task")
    public boolean beginTask(){
        return downLoadManager.createTask();
    }
}

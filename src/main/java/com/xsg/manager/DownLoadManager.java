package com.xsg.manager;

import com.xsg.entity.DownLoadCenterRecord;
import com.xsg.service.DownLoadService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: TODO
 */
@Component
public class DownLoadManager {


    @Setter(onMethod_ = {@Autowired})
    private DownLoadService downLoadService;


    public boolean createTask() {
        // 创建一条任务记录
        String uuid = UUID.randomUUID().toString().replace("-", "");
        DownLoadCenterRecord record = new DownLoadCenterRecord();
        record.setFileName(uuid + ".xlsx");
        record.setId(uuid);
        // 任务导出中
        record.setStatus(0);
        downLoadService.createDownloadSingleTask(uuid);
        return true;
    }
}

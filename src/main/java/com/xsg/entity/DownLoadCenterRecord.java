package com.xsg.entity;

import lombok.Data;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: TODO
 */
@Data
public class DownLoadCenterRecord {
    private String id;
    private String fileName;
    private String fileUrl;
    private Integer status;
    private String remark;
}

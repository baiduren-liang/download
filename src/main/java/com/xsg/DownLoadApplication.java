package com.xsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: ganglia
 * @CreateTime: 2025-03-19
 * @Description: 启动器
 */
@EnableAsync
@SpringBootApplication
public class DownLoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownLoadApplication.class,args);
    }
}

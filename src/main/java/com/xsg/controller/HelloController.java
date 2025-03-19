package com.xsg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liangcf
 * @CreateTime: 2025-03-19
 * @Description: TODO
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/print")
    private String print(){
        return "print hello";
    }
}

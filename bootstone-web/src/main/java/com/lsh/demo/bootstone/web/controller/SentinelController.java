package com.lsh.demo.bootstone.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sentinel.SentinelBootDemo;

import javax.annotation.Resource;

/**
 * Created by lsh on 2020-04-15.
 */
@RestController
@Component
@RequestMapping("/rate")
public class SentinelController {

    @Resource
    private SentinelBootDemo demo;

    @RequestMapping("/hello")
    @SentinelResource("aa")
    public String sayHello(){
        String result = demo.sayHello("lsh");
        return result;
    }
}

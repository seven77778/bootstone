package com.lsh.demo.bootstone.web.controller;


import com.lsh.demo.bootstone.service.SmsSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("stater")
public class MySpringbootStarterController {


    @Resource(name = "aliyunSmsSenderImpl")
    private SmsSender smsSender;

    @RequestMapping("/sms")
    public void test(){
        smsSender.send("hahaha");
        System.out.println("success");
    }
}

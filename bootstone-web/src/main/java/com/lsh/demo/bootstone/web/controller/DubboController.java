package com.lsh.demo.bootstone.web.controller;

import com.lsh.scm.dubbo.MyDubboService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Resource
    @Lazy
    private MyDubboService myDubboService;

    @PostMapping("dubbo1")
    public String getDubbo1() throws Exception {
        for (int i = 5; i < 10; i++) {
            myDubboService.getName();
        }
        return "ok";
    }
}

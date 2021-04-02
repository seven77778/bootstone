package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.workorder.ProjectOrderService;
import com.lsh.scm.dubbo.MyDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Autowired
    private ProjectOrderService projectOrderService;

    @Resource
    private MyDubboService myDubboService;

    @PostMapping("dubbo1")
    public String getDubbo1() throws Exception {
        for (int i = 5; i < 10; i++) {
            myDubboService.getName();
        }
        return "ok";
    }
}

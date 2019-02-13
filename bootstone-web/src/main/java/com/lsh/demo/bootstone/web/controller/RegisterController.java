package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.common.LogFactory;
import com.lsh.demo.bootstone.study.strategy.PayService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by lsh on 2018/12/8.
 */

@RestController
@Component
@RequestMapping("/bootstone")
public class RegisterController {

    @Resource
    private PayService payService;

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name",required = false) String name){
//        payService.payDiscount("",100);
        LogFactory.bootStone.info("hello~~");
        LogFactory.study.error("good good study");
        LogFactory.CONSOLE.error("i am console");
        return "hello " + name + " , " + LocalDateTime.now();
    }

}

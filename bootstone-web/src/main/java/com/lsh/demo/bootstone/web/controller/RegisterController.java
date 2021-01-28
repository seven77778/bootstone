package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.basic.strategy.PayService;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
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
        payService.payDiscount("vip",100);
        BootStoneLog.bootStone.info("hello~~");
        BootStoneLog.study.error("good good basic");
        BootStoneLog.CONSOLE.error("i am console");
        return "hello " + name + " , " + LocalDateTime.now();
    }

    @RequestMapping("discount")
    public String discount(@RequestParam(name = "type",required = false) String type){
       double result =  payService.payDiscount(type,100) ;
       return "您是" +type + "会员，享受的价格为 " + result;
    }

    public static void main(String[] args) {
        System.out.println((float) 61/10);
    }
}

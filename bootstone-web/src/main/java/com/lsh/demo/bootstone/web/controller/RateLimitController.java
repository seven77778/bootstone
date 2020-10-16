package com.lsh.demo.bootstone.web.controller;

import biz.limit.anno.RateLimitAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rate")
public class RateLimitController {

    @RequestMapping("num1")
    @RateLimitAnno(type = "memory",num = 3,name="method1",waitFor = 1)
    public String rateByQps(){
        return "ok~";
    }

    @RequestMapping("num2")
    @RateLimitAnno(type = "memory",num = 5,name="method2",waitFor = 1)
    public String rateByQps2()throws Exception{
        Thread.sleep(10000);
        return "ok~";
    }

    @RequestMapping("num3")
    @RateLimitAnno(type = "redis-num",num = 5,name="method3",waitFor = 1)
    public String rateByQps3()throws Exception{
        Thread.sleep(10000);
        return "ok~";
    }

}

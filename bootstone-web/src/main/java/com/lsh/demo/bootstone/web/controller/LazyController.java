package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.annolearn.lazy.MyLazyTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/lazy")
public class LazyController {

    @Resource
    @Lazy
    private MyLazyTest MyLazyTest;



    /**
     * 使用@bean注解的来测试
     */
    @RequestMapping("test2")
    public String testLazyWithBean(){
        return MyLazyTest.getUtil();
    }


}

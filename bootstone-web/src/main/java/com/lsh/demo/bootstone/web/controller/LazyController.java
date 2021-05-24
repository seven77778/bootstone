package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.annolearn.lazy.MyLazyUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/lazy")
public class LazyController {

    @Resource
    @Lazy
    private MyLazyUtil util;

    /**
     * 两个地方都要加 @lazy
     */
    @RequestMapping("test")
    public String testLazyWithBean(){
        return util.getStr();
    }


}

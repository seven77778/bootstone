package com.lsh.demo.annolearn.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Lazy
public class MyLazyTest {

    @Resource
    private MyLazyUtil util;

    public  String getUtil(){
        return util.getStr();
    }



}

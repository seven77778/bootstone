package com.lsh.demo.bootstone.dubbo.service.impl;


import com.lsh.demo.bootstone.dubbo.service.DubboThreadTestService;

public class DubboThreadTestServiceImpl implements DubboThreadTestService {
    @Override
    public String simpleGet() {
        return "lsh";
    }

    @Override
    public String longTimeGet() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "lsh";
    }

    @Override
    public String get() {
        return null;
    }
}

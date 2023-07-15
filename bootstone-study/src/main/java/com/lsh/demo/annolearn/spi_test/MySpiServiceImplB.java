package com.lsh.demo.annolearn.spi_test;

import org.apache.dubbo.common.URL;
import org.springframework.stereotype.Component;

/**
 * @author lsh
 * @date 2023/4/19 11:18
 */
@Component
public class MySpiServiceImplB implements MySpiService {
    @Override
    public String hello(URL url, String s) {
        return null;
    }

    @Override
    public void printA(URL url) {
        System.out.println("this is A:MySpiServiceImplb");

    }

    @Override
    public void printB(URL url) {
        System.out.println("this is b:MySpiServiceImplb");
    }

    @Override
    public void printC(URL url) {
        System.out.println("this is c:MySpiServiceImplb");
    }
}

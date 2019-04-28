package com.lsh.demo.bootstone.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.bootstone.spring.ioc.iocdemo1.MoAttack;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lsh
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.lsh.demo.bootstone.workorder"})
@ComponentScan(basePackages = {"com.lsh.demo.bootstone.study","com.lsh.demo.bootstone.web.controller","com.lsh.demo.spring","xml",
        "com.bootstone.spring.ioc.iocdemo1"})
public class BootStoneWebApplication {
    private static ArrayList li = new ArrayList();

    MoAttack moAttack =new MoAttack();
    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }
}

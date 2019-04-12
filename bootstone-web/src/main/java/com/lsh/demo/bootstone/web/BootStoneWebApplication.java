package com.lsh.demo.bootstone.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lsh
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.lsh.demo.bootstone.workorder"})
@ComponentScan(basePackages = {"com.lsh.demo.bootstone.study","com.lsh.demo.bootstone.web.controller","com.lsh.demo.spring","xml"})
public class BootStoneWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }
}

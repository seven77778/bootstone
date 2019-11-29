package com.lsh.demo.bootstone.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lsh
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.lsh.demo.bootstone.workorder"})
@ComponentScan(basePackages = {"com.lsh.demo.basic","com.lsh.demo.bootstone.web.controller","com.lsh.demo.spring","xml",
        "com.bootstone.spring.ioc.iocdemo1","com.lsh.demo.bootstone.service","com.lsh.demo.bootstone.web.point",
"com.lsh.demo.bootstone.dao.mysql"})
@MapperScan("com.lsh.demo.bootstone.dao")
public class BootStoneWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }
}

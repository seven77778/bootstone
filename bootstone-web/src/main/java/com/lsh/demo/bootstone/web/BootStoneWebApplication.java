package com.lsh.demo.bootstone.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lsh
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lsh.demo.bootstone.study","com.lsh.demo.bootstone.web.controller"})
public class BootStoneWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootStoneWebApplication.class, args);
    }
}

package com.lsh.demo.bootstone.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by lsh on 2019/2/12.
 */
@RestController
@Component
@RequestMapping("/hello")
public class HelloController {

    @Value("${valuetest.test1}")
    private String test1;

    @GetMapping("/test")
    public String test(){
        System.out.println(test1);
        return test1;
    }

    /**
     * 入参的name为空，name.get()-java.util.NoSuchElementException: No value present
     */
    @GetMapping("get")
    public String getUser(String id, Optional<String> name){
        return "hello, "+name.get()+id;
    }
}

package com.lsh.demo.bootstone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by lsh on 2018/12/8.
 */
@RestController
@RequestMapping("/bootstone")
public class RegisterController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name") String name){
        return "hello " + name + " , " + LocalDateTime.now();
    }

}

package com.lsh.demo.bootstone.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lsh on 2019-08-20.
 */
@RestController
@Component
@RequestMapping("/77")
public class QIQITestController {

    @GetMapping("/test")
    public String getTest(){
        return "77 test";
    }

    @GetMapping("/test2")
    public String getTest2(@RequestParam(name = "name",required = false)String name) {
        return "77 test" + name;
    }

    @GetMapping("/test3")
    public String getTest3(String username,String password) {

        //模拟一个简单的用户登录操作哈，验证用户名和密码对不对

       if("77".equals(username) && "123456".equals(password) ){
           return "欢迎登录手机银行，您的余额还有9999999999元RMB";
       }else {
           return "用户名/密码错误";
       }


    }

    @PostMapping("/post1")
    public String post1(){
        return "this is post";
    }

    @PostMapping("/post2")
    public String post2(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name").toString();
        String age = jsonObject.getString("age").toString();

        return "hello," + name + " " +age;
    }
}

package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lsh on 2019-11-21.
 */
@RestController
@RequestMapping("/service")
public class ServiceController {


    @Resource
    private CommonService commonService;


    @GetMapping("/rest")
    public String getRestSeconds(String key){
        System.out.println("收到前端的key " + key);
        if(StringUtils.isBlank(key)){
            key = "default_key";
        }
        return commonService.getRestSeconds(key);
    }

    @RequestMapping("/runtime")
    public String getRunTime(){
        return commonService.invokeSql2("");
    }
}

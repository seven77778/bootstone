package com.lsh.demo.bootstone.web.controller;

import com.alibaba.fastjson.JSON;
import com.lsh.demo.bootstone.web.common.ViewResult;
import com.lsh.demo.bootstone.workorder.ProjectOrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2019/2/13.
 * @author
 */
@RestController
@RequestMapping("/order")
@Component
public class ProjectOrderController {

    @Resource
    ProjectOrderService service;

    @GetMapping("/query")
    public ViewResult query(Long id){
        return ViewResult.success(service.getorders(id).getModule());
    }

    @PostMapping("/list")
    public ViewResult getWorkOrderList( ){
        List list = new ArrayList();
        list.add("hotel_2018011222141413_65");
        list.add("hotel_2018501220143330_10");
        list.add("hotel_2018011222141413_66");
        return ViewResult.copyResult(service.getWorkOrderList(list));
    }

    public static void main(String[] args) {
    }
}

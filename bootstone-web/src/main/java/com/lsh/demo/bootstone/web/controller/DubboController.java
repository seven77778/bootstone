package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.workorder.ProjectOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Resource
    private ProjectOrderService projectOrderService;

    @RequestMapping("dubbo1")
    public String getDubbo1() throws Exception{
      return   projectOrderService.give();
    }
}

package com.lsh.demo.bootstone.web;

import com.lsh.demo.spring.service.BaseService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

/**
 * Created by lsh on 2019/2/12.
 * testsss
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStoneWebApplication.class)
@WebAppConfiguration
public class Test {
    /**
     * list注入
     */
    @Autowired
    private List<BaseService> serviceList;

    /**
     * Map注入
     */
    @Autowired
    private Map<String,BaseService> serviceMap;


    @org.junit.Test
    public void test(){
        System.out.println(serviceList.size());
        System.out.println(serviceMap.size());
    }
}

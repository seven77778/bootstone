package com.lsh.demo.spring;

import com.lsh.demo.spring.service.BaseService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lsh on 2019/2/12.
 * 注入
 * @Autowire byType注入
 * @resource byName注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class Inject {

    /**
     * list注入
     */
    @Resource
    private List<BaseService> serviceList;

    /**
     * Map注入
     */
    @Resource
    private Map<String,BaseService> serviceMap;


    /**
     * 直接运行 @Test 不可行
     *
     * fix throw error
     *
     * Is it an abstract class?
     */
    @Test
    public void test(){
        System.out.println(serviceList.size());
        System.out.println(serviceMap.size());
    }

}

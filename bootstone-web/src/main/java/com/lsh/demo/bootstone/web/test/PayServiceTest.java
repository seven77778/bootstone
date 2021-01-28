package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.basic.strategy.PayService;
import com.lsh.demo.basic.strategy.PayService2;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})
public class PayServiceTest {

    /**
     * PayService 中不用写什么，都是这个@resource注入的
     */
    @Resource
    private PayService payService;

    @Resource
    private PayService2 payService2;

    @Test
    public void test(){
        System.out.println(payService.payDiscount("vip",100));
        System.out.println(payService2.payDiscount("vip",100));
        System.out.println(1233);
    }
}

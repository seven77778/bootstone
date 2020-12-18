package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import com.lsh.demo.bootstone.web.listener.MyApplicationEvent;
import com.lsh.demo.bootstone.web.listener.MyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootStoneWebApplication.class})
public class EventTest {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 一次完整的监听
     * ApplicationContext 可以这样引入
     *
     * 监听的机制  todo
     * listener的发送消息，和 线程池的异步调用是不是一个路子 TODO
     *
     */
    @Test
    public void testMyEvent(){
        MyEvent event = new MyEvent("123");
        event.setData("hahaha");
        applicationContext.publishEvent(event);

        ApplicationEvent applicationEvent = new MyApplicationEvent("11");
        applicationContext.publishEvent(applicationEvent);
    }

}

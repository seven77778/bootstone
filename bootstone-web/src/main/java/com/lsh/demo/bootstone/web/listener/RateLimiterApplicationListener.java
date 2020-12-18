package com.lsh.demo.bootstone.web.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;


public class RateLimiterApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("-----RateLimiterApplicationListener------------");
    }
}

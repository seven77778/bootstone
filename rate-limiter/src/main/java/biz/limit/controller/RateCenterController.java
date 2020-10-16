package biz.limit.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RateCenterController  extends AbstractConfiger implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

    @Override
    protected void afterAdd() {

    }

    @Override
    protected void afterDelete() {

    }
}

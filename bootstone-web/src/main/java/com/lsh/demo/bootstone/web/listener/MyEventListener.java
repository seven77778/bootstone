package com.lsh.demo.bootstone.web.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener
    public void getEvent(MyEvent event){
        System.out.println("this is MyEventListener get event");
        System.out.println(event.getData());

    }
}

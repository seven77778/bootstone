package com.lsh.demo.bootstone.web.listener;

import org.springframework.context.ApplicationEvent;


public class MyEvent extends ApplicationEvent {


    private String data;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
        System.out.println("this is myEvent");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}

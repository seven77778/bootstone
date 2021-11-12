package com.lsh.demo.aaamianshi.springcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyC {
    @Autowired
    private MyD m;
}

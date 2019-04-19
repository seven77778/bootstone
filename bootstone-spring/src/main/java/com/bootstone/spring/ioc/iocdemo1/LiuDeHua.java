package com.bootstone.spring.ioc.iocdemo1;

import org.springframework.stereotype.Component;

/**
 * Created by lsh on 2019/4/18.
 */
@Component("123")
public class LiuDeHua implements GeLi{

    private String name;
    private String age;


    @Override
    public void responseAsk(String str) {
        System.out.println("LiuDeHua " + str);
    }
}

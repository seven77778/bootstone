package com.bootstone.spring.ioc.iocdemo1;

import org.springframework.stereotype.Component;

/**
 * Created by lsh on 2019/4/18.
 */
@Component("xiao")
public class XiaoXianRou implements GeLi {

    @Override
    public void responseAsk(String str) {
        System.out.println("我是小鲜肉，我演技很lan");
    }
}

package com.lsh.demo.bootstone.workorder;

import com.alibaba.dubbo.config.annotation.Reference;
import org.helixcs.springboot.samples.dubboclient.SayHelloInterface;
import org.springframework.stereotype.Component;

/**
 * Created by lsh on 2019/4/12.
 */
@Component
public class DubboTest {

    @Reference
    private SayHelloInterface sayHelloInterface;

    public String test(){

        return sayHelloInterface.sayHello("hehe");
    }

}

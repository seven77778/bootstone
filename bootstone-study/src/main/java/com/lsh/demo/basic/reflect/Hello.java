package com.lsh.demo.basic.reflect;

/**
 * Created by lsh on 2018/10/31.
 *
 * @author lsh
 * @date 2018/10/31
 */
public class Hello {

    public String say(boolean b) throws Exception {
        System.out.println("this is Hello say");
        if (b) {
            throw new Exception("say_Exception");
        }
        return "hello";
    }
}

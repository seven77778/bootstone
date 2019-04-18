package com.lsh.demo.designmode.decorator.demo2_inputstream;

/**
 * Created by lsh on 2019/4/17.
 * 对象字节流
 */
public class MyObjectInputStream implements MyInputStream {


    @Override
    public int read() {
        System.out.println("this is MyObjectInputStream");
        return 0;
    }
}

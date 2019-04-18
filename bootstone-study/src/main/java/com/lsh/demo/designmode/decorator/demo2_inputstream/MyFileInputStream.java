package com.lsh.demo.designmode.decorator.demo2_inputstream;

/**
 * Created by lsh on 2019/4/17.
 *
 * 文件字节流
 */
public class MyFileInputStream implements MyInputStream {

    @Override
    public int read() {
        System.out.println("this is MyFileInputStream");
        return 0;
    }
}

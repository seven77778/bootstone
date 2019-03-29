package com.lsh.demo.network.serial;

/**
 * Created by lsh on 2019/3/8.
 */
public class TestDemo {

    public static void main(String[] args) {

        Thread thread=new Thread(new SerialPortTest1());
        thread.start();
    }

}
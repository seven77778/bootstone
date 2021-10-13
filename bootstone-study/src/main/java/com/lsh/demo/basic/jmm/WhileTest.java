package com.lsh.demo.basic.jmm;

public class WhileTest {

    static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("begin=" + num);
            num++;
            System.out.println("middle=" + num);

        }).start();

        while (num == 0){
            System.out.println(1111);
        }
        System.out.println("end=" + num);
    }
}

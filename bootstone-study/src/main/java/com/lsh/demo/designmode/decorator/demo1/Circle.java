package com.lsh.demo.designmode.decorator.demo1;

/**
 * Created by lsh on 2019/4/17.
 * circle 圆
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("this is Circle");
    }
}

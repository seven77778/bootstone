package com.lsh.demo.designmode.decorator.demo1;

/**
 * Created by lsh on 2019/4/17.
 * decorator 装饰者
 * step3 - 创建实现了shape接口的抽象装饰类
 *
 */
public abstract class ShapeDecorator {

    protected Shape shape;

    public ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    public void draw(){
        shape.draw();
    }

}

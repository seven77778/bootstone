package com.lsh.demo.designmode.decorator.demo1;

/**
 * Created by lsh on 2019/4/17.
 */
public class RedShapeDecorator extends ShapeDecorator implements Shape{

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw(){
        System.out.println("this is Red");
        shape.draw();

    }

    private void setRedBorder(Shape redBorder){
        System.out.println("Red");
    }


}

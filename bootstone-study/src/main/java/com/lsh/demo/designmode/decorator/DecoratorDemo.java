package com.lsh.demo.designmode.decorator;

import com.lsh.demo.designmode.decorator.demo1.Circle;
import com.lsh.demo.designmode.decorator.demo1.Rectangle;
import com.lsh.demo.designmode.decorator.demo1.RedShapeDecorator;
import com.lsh.demo.designmode.decorator.demo1.Shape;

/**
 * Created by lsh on 2019/4/17.
 * 修饰器模式
 *
 * 使用 RedShapeDecorator 来装饰 Shape 对象
 *
 * 在不想增加很多子类的情况下扩展类
 * 通常给对象添加功能，要么直接修改对象添加相应的功能，要么派生子类来扩展，抑或是使用对象组合的方式。
 * 显然，直接修改对应的类的方式并不可取，
 * 在面向对象的设计中，我们应该尽量使用组合对象而不是继承对象来扩展和复用功能，装饰器模式就是基于对象组合的方式的
 *
 * 装饰器模式以对客户端透明的方式动态地给一个对象附加上了更多的责任。
 *
 * 换言之，客户端并不会角色对象在装饰前和装饰后有什么不同。装饰器模式可以在不用创建更多子类的情况下，将对象的功能加以扩展。
 *
 * 装饰器模式中的角色有：
 *
 * 1、抽象构件角色
 *
 * 给出一个抽象接口，以规范准备接受附加责任的对象
 *
 * 2、具体构件角色
 *
 * 定义一个将要接受附加责任的类
 *
 * 3、装饰角色
 *
 * 持有一个构建对象的实例，并定义一个与抽象构件接口一致的接口
 *
 * 4、具体装饰角色
 *
 * 负责给构建对象贴上附加的责任
 */
public class DecoratorDemo {

    public static void main(String[] args) {

        Shape shape = new Circle();
        shape.draw();

        Shape shape1 = new RedShapeDecorator(new Rectangle());
        shape1.draw();
    }
}

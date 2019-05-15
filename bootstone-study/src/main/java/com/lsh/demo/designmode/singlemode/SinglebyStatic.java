package com.lsh.demo.designmode.singlemode;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/13 17:20.
 *
 * @author lsh
 * @date 2018/11/13
 *
 * 使用静态内部类 实现 单例模式
 *
 * 优点：1.实现了延迟加载，getSingle() 第一次用的时候才会加载
 * 2、线程安全，内部静态类
 *
 * 缺点：1.都需要额外的工作(Serializable、transient、readResolve())来实现序列化，否则每次反序列化一个序列化的对象实例时都会创建一个新的实例
 * 2.可能会有人使用反射强行调用我们的私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）
 *
 * 单例模式使用场景：
 * 1.Windows的Task Manager（任务管理器）就是很典型的单例模式（这个很熟悉吧），想想看，是不是呢，你能打开两个windows task manager吗，同回收站
 * 2.计数器
 * 3.数据库连接池，线程池
 * 4.HttpApplication 也是单位例的典型应用 todo
 */
public class SinglebyStatic {

    private SinglebyStatic(){

    }

    private static class Holder{
        private static SinglebyStatic singlebyStatic = new SinglebyStatic();
    }

    private static SinglebyStatic getSingle(){
        return Holder.singlebyStatic;
    }

    /**
     *  Test class should have exactly one public constructor
     *
     */
    @Test
    public void  test(){
        SinglebyStatic singlebyStatic = SinglebyStatic.getSingle();
        SinglebyStatic singlebyStatic2 = SinglebyStatic.getSingle();
        System.out.println(singlebyStatic == singlebyStatic2);
    }

    public static void main(String[] args) {
        SinglebyStatic singlebyStatic = SinglebyStatic.getSingle();
        SinglebyStatic singlebyStatic2 = SinglebyStatic.getSingle();
        SinglebyStatic singlebyStatic3 = new SinglebyStatic();

        System.out.println(singlebyStatic == singlebyStatic2);//true
        System.out.println(singlebyStatic == singlebyStatic3);//false
    }
}

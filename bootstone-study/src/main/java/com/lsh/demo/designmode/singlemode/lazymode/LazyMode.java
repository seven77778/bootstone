package com.lsh.demo.designmode.singlemode.lazymode;

import com.lsh.demo.designmode.singlemode.hungrymode.HungryMode;

/**
 * Created by lsh on 2019-05-13.
 *
 * 属于单例模式
 * 为什么要有单例模式
 * windows资源管理器 打印机后台程序
 *
 * 懒汉模式  -- 线程不安全
 *
 */
public class LazyMode {

    public static LazyMode lazyMode = null;

    /**
     * 1.0
     * @return
     */
    public static LazyMode getInstance1() {
        if(lazyMode ==null){
            lazyMode = new LazyMode();
        }
        return lazyMode;
    }


    /**
     * 2.0
     *
     * 懒汉模式V2.0解决了1.0中可能会new两次对象的问题，但是依然有问题。
     *
     * 指令重排序
     * int a=1;
     *
     * int b=a+1;
     *
     * int c=2;
     * 可能先执行 int c=2  指令重排序后，要保证在单个线程里，执行结果和重排序前是等效的
     *
     * 再来看我们的问题：
     * 当多线程并发的时候，假如第一个线程成功获取锁并进入if块执行singleton=new Singleton()，
     *
     * 这句代码我们可以看成三步操作：
     *
     * ①在堆内存中划分一个Singleton对象实体的空间
     * ②初始化堆内存中对象实例的数据（字段等）
     * ③将singleton变量通过指针指向生成的对象实体
     *
     * 3.0 即给 lazymode 加 volatile ，禁止重排序
     *
     * see  https://www.cnblogs.com/chenliyang/p/6548374.html
     *
     * @return
     */
    public static LazyMode getInstance(){
        if (lazyMode==null) {
            synchronized (LazyMode.class) {
                if (lazyMode==null) {
                    lazyMode=new LazyMode();
                }
            }
        }
        return lazyMode;
    }


    /**
     * 静态内部类Singleton在初始化过程中是不会被加载的，类似懒汉模式
     */
    private static class SingletonHolder{
        private static HungryMode instance=new HungryMode();
    }

    public static HungryMode getInstance2(){
        return SingletonHolder.instance;
    }

}

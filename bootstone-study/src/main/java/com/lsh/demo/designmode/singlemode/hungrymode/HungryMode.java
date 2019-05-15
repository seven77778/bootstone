package com.lsh.demo.designmode.singlemode.hungrymode;

/**
 * Created by lsh on 2019-05-13.
 *
 * 饿汉模式 -- 线程安全
 *
 * 因为饿汉模式先天就没有线程安全问题，而且也并不像网上说的那样，上来就要创建实例。
 *
 * 网上一般的说法是，饿汉模式会导致程序启动慢，因为一上来就要创建实例。
 *
 * 相信这么说的人一定是不了解java的类加载机制 ,类的加载分为5个步骤：加载、验证、准备、解析、初始化-> 使用、卸载
 *
 * “懒汉模式”实现复杂而且没有任何独占优点，“饿汉模式”完胜
 *
 * 缺点： 它是加载类时创建实例、所以如果是一个工厂模式、缓存了很多实例、那么就得考虑效率问题，
 * 因为这个类一加载则把所有实例不管用不用一块创建
 */
public class HungryMode {

    private static final HungryMode singleton=new HungryMode();

    public static HungryMode getInstance(){
        return singleton;
    }



    /**
     * 单例的优雅模式 -- 枚举
     *
     */

}

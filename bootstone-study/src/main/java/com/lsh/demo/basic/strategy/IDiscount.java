package com.lsh.demo.basic.strategy;

/**
 * Created by LSH on 2018/11/14.
 *
 * @author LSH
 * @date 2018/11/14
 *
 * 策略模式 定义接口
 * 策略模式实例化对象的操作在客户端
 * spring -- SimpleInstantiationStrategy
 *
 * 优点：易于扩展，自由切换，新增策略对其他无影响
 * 缺点：1、当策略较多的时候，维护困难
 * 2、需要对 外暴露所有的策略
 *
 */
public interface IDiscount {

    /**
     * 获取策略类型
     */
    String getType();

    /**
     * 计算金额
     */
    Double discount(double cost);
}

package com.lsh.demo.basic.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2018/11/14.
 *
 * @author LSH
 * @date 2018/11/14
 * <p>
 * 收费服务
 * 1.没有无参数的构造方法， Caused by: java.lang.NoSuchMethodException: com.designmode.strategy.PayService.<init>()
 * spring boot 直接执行，spring 中必须有无参的构造方法
 * <p>
 * 2.list如何初始化 ？ -- @service 注入
 * <p>
 * 3.@Autowired
 * List<IDiscount> discounts = new ArrayList<>();  -- 初始化顺序？
 * 构造方法先于 @service执行，通过构造器实例化的bean，才能注入啊
 */


@Component
public class PayService2 {

    @Autowired
    private static Map<String, IDiscount> map = new HashMap<>();

    public PayService2(List<IDiscount> list) {
        System.out.println(list);
        list.forEach(iDiscount -> map.put(iDiscount.getType(), iDiscount));
    }

    //存在无参构造，还是优先执行的 fixme
    public PayService2() {
    }

    public double payDiscount(String type, double cost) {
        System.out.println(map.size());
        return map.get(type).discount(cost);
    }
}

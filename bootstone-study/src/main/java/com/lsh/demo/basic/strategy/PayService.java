package com.lsh.demo.basic.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
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
public class PayService {


    //Caused by: java.util.ConcurrentModificationException 目标map加上this
    //map遍历的时候修改抛出ConcurrentModificationException
    private final Map<String, IDiscount> map = new HashMap<>();

    public PayService(Map<String, IDiscount> map) {
        map.forEach((x, y) -> this.map.put(y.getType(), y));
        System.out.println(map);
    }

    //
    public double payDiscount(String type, double cost) {
        System.out.println(map.size());
        return map.get(type).discount(cost);
    }
}

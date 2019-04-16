package com.lsh.demo.bootstone.study.strategy;

import org.springframework.stereotype.Service;

/**
 * Created by LSH on 2018/11/14.
 *
 * @author LSH
 * @date 2018/11/14
 */
@Service
public class VipDiscount implements IDiscount {
    @Override
    public String getType() {
        return "vip";
    }

    @Override
    public Double discount(  double cost) {
        System.out.println("use VipDiscount");
        return cost * 0.95;
    }
}

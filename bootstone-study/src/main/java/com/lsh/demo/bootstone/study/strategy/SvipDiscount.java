package com.lsh.demo.bootstone.study.strategy;

import org.springframework.stereotype.Service;

/**
 * Created by LSH on 2018/11/14.
 *
 * @author LSH
 * @date 2018/11/14
 */
@Service
public class SvipDiscount implements IDiscount {
    @Override
    public String getType() {
        return "svip";
    }

    @Override
    public Double discount(  double cost) {
        System.out.println("use SvipDiscount");
        return cost * 0.5;
    }
}

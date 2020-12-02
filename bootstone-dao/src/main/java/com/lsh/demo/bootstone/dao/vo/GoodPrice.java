package com.lsh.demo.bootstone.dao.vo;

import lombok.Data;

import java.util.List;

/**
 * 具体的商品
 */
@Data
public class GoodPrice {

    private String name;

    private List<PriceDate> priceDates;
}

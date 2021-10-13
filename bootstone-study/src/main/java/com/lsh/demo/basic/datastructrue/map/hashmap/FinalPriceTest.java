package com.lsh.demo.basic.datastructrue.map.hashmap;

public class FinalPriceTest {

    static FinalPriceTest P = new FinalPriceTest(2.7);
    final static double apple = 20;//加上final后 输出结果为17.3
    double Price;

    public FinalPriceTest(double orange) {
        Price = apple - orange;
    }
}

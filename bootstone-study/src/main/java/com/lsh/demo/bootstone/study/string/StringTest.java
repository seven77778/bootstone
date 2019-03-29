package com.lsh.demo.bootstone.study.string;

import org.junit.Test;

/**
 * Created by lsh on 2019/3/29.
 */
public class StringTest {

    /**
     * 替换身份证年月 为 ****
     * replace 可能会误操作 推荐StringBuilder
     */
    @Test
    public void test(){
        String card = "888888196601101966";
        System.out.println(card.replace(card.substring(6,10),"****"));
        StringBuilder stringBuilder = new StringBuilder(card);
        System.out.println(stringBuilder.replace(6,10,"****"));
    }

}

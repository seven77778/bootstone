package com.lsh.demo.bootstone.web.test;

import org.junit.Test;

public class FastTest {

    /**
     * 去掉字符串开头的若干个0
     * 00020001002
     * 212
     * 20001002 -- ok
     */
    @Test
    public void replaceZero(){
        String str = "00020001002";
        String res = str.replace("0", "");
        System.out.println(str);
        System.out.println(res);
        String res2 = str.replaceFirst("^0*", "");
        System.out.println(res2);
    }

}

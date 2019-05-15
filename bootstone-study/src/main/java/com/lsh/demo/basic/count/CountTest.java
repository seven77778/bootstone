package com.lsh.demo.basic.count;

import org.junit.Test;

/**
 * Created by lsh on 2019/3/8.
 */
public class CountTest {

    @Test
    public void test(){
        int sum = 0;
        byte a = 1;
        System.out.println((sum + a)& 0xff );
    }

}

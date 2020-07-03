package com.lsh.demo.basic.count;

import org.junit.Test;

/**
 * Created by lsh on 2019/3/8.
 *
 * byte int
 */
public class CountTest {

    @Test
    public void test() {
        int sum = 0;
        Byte b=1;
        byte a = 109;
        System.out.println( b.getClass());
        System.out.println((sum + a) & 0xff);
    }

}

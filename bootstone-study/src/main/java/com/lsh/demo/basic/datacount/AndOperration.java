package com.lsh.demo.basic.datacount;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/13 10:16.
 *
 * @author lsh
 * @date 2018/11/13
 */
public class AndOperration {

    /**
     * 与运算 两个都是1，结果才是1；一个为0，结果是0
     * 或运算 一个是1，就是1
     * 异或运算 不同的是1
     * <p>
     * 用途：
     * 1.判断int型变量a是奇数还是偶数，a & 1 = 0 偶数
     * 2.两个int型互换，高效
     * x ^= y;
     * y ^= x;
     * x ^= y;
     */
    @Test
    public void test() {
        System.out.println(1 & 1);
        System.out.println(1 & 2);
        System.out.println(2 | 2);
        System.out.println(1 | 2);

        System.out.println(99886 & 1);//偶数

        System.out.println(209 ^ 1);
        System.out.println(1 ^ 234);

        //一倍 2 * 2的1次幂
        System.out.println(2 << 1);
        //
        System.out.println(2 << 3);
        System.out.println(2 << 4);
        //1 * 2的4次幂
        System.out.println(1 << 4);

        //取模运算
        System.out.println(Math.floorMod(9, 7));
    }

    //int互换
    @Test
    public void test22() {
        int a = 108;
        int b = 77;
        int c = a ^ b;
        int d = c ^ a;
        int f = d ^ c;

        System.out.println(c);
        System.out.println(d);
        System.out.println(f);
    }
}

package com.lsh.demo.basic.datacount;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/5.
 *
 * @author lsh
 * @date 2018/11/05
 */
public class ByteTest {

    /**
     *byte :-128～+127
     * */
    @Test
    public void test() throws Exception {
        byte a = 1;
        int b = 1;
        System.out.println(a == b);//true
        Byte byte1 = new Byte("1");
        System.out.println(a == byte1);//true
        byte c = 127;
        byte[] aaa = new byte[2];
        aaa[0] =1;

        //byte 转 String
        byte[] strbyte = {1, 2, 3};
        String s = new String(strbyte,"UTF-8");
        System.out.println("byte转String "+s.toString());//s乱码

        //
        String s2 = "hello";
        byte[] s2b = s2.getBytes();
        System.out.println(new String(s2b));
        System.out.println(s2b);
    }

    @Test
    public void test2(){
        Byte b = new Byte("333");
        System.out.println(b);//java.lang.NumberFormatException: Value out of range. Value:"333" Radix:10
    }

    @Test
    public void test3(){
        byte b = (byte)222;
        System.out.println(b);//超过127负数溢出
    }
}

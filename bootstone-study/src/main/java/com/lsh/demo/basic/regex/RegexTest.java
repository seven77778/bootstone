package com.lsh.demo.basic.regex;

import org.junit.Test;

/**
 * Created by lsh on 2020-04-13.
 * regex
 */
public class RegexTest {

    @Test //替换功能
    public void test1(){
        String s = "12342jasfkgnas234";
        //把字符串里面的数字替换成*
        // [0-9] = \\d
//        String regex = "\\d";
        String regex = "[0-9]";
        String ss = "*";
        String result = s.replaceAll(regex,ss);
        System.out.println(result);
    }

    @Test //判断功能
    public void test2(){
        String s = "123aa222";
        String regex = "[0-9]+[a-z]+\\d+";
        boolean result = s.matches(regex);
        System.out.println(result);
    }

}

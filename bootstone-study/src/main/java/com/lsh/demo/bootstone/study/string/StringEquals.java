package com.lsh.demo.bootstone.study.string;

import org.junit.Test;

/**
 * Created by lsh on 2019-04-29.
 *
 * jdk1.6中字符串常量池存放在永久代中：
 *
 * 使用intern()方法时，查询字符串常量池是否存在当前字符串，若不存在则将当前字符串复制到字符串常量池中，并返回字符串常量池中的引用。
 *
 * jdk1.7中字符串常量池存放在堆中：
 *
 * 当使用intern()方法时，先查询字符串常量池是否存在当前字符串，若字符串常量池中不存在则再从堆中查询，然后存储并返回相关引用；
 *
 * 若都不存在则将当前字符串复制到字符串常量池中，并返回字符串常量池中的引用
 *
 * ***********
 *
 *    jdk1.6中只能查询或创建在字符串常量池；
 *
 * 　　jdk1.7中会先查询字符串常量池，若没有又会到堆中再去查询并存储堆的引用，然后返回
 *
 * see https://www.cnblogs.com/aloenfs/p/9127353.html
 */
public class StringEquals {
    /**
     *
     * out:
     * String a = "ab";
     String b = "c";
     (new StringBuilder()).append(a).append(b).toString();
     String c = "1";
     String d = "1";
     (new StringBuilder()).append(c).append(d).toString();
     String s = "aaaaaasccccccccccccccddddddddddddddccccccccccccccccccccccccc";
     *
     * 1.字面常量的相加，直接拼接好
     * 2.String 对象相加，new StringBuilder
     *
     * */
    @Test
    public void test(){
        String a = "a" + "b";
        String b = "c";
        String str = a+b;
        String c = "1";
        String d = "1";
        String e = c + d;
        String s = "aaaaaascccccccccccccc"+"ddddddddddddddccccccccccccccccccccccccc";
    }


    @Test
    public void test2(){
        String s = new String("a" +"b");
        String a = new String("a") +"b";
        String b = "ab";
        System.out.println(s.intern() == b);
        System.out.println(a.intern() == b);

        String c = "aa";
        String d = "a" +"a";
        System.out.println(c == d);
    }
}

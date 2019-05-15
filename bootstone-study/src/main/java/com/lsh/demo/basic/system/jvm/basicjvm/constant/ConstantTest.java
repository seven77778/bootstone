package com.lsh.demo.basic.system.jvm.basicjvm.constant;

import org.junit.Test;

/**
 * Created by lsh on 2018/10/29.
 *
 * @author lsh
 * @date 2018/10/29
 */
public class ConstantTest {

    /**
     * jdk1.7以后，常量池也是堆中的一部分且常量池可以存引用
     * */
    @Test
    public void test1(){
        String str = "abc";//直接存储常量池
        String str2 = new String("abc");//同时生成堆中的对象 以及常量池中abc的对象，但是此时str2是指向堆中的对象的
        System.out.println(str.equals(str2));//true,String重写了equals
        System.out.println(str == str2);//fasle,==比较的是地址
        String str3 = "a" + "bc";
        System.out.println(str3 == str);//true,字面常量类型的String相加， 也会存储常量池

        String str4 = "a" + new String("bc");
        //System.out.println(str4 == str);//false,理由见上一条,new String()不属于字面常量

        String str5 = "a" + new String("bc").intern();
        System.out.println(str5 == str);//fasle,常量池中存入bc，常量池不会自动生成abc

        String str6 = new String("abc").intern();
        System.out.println(str6 == str);//true

        String str7 = new StringBuilder("ab").append("c").toString();
        System.out.println(str7 == str7.intern());//false

    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        String s = "abc";
        String s1 = new String("abc");
        String s3 = s1.intern();
        System.out.println(s == s1);
        System.out.println(s == s3);
    }


}

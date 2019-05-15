package com.lsh.demo.basic.system.jvm.basicjvm.runtimememroy;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2018/10/30.
 *
 * @author lsh
 * @date 2018/10/30
 */
public class MetaSpaceTest {

    /**
     * 测试java8版本中，常量池存储在哪里
     * */
    @Test
    public void test(){
        String str[] = new String[1];
        System.out.println(System.getProperty("java.version"));
        for(int i=0;;i++){
            String newString = "newString" +i;
            newString.intern();
            System.out.println(i);
            str[0] = newString;
            //String n = new String(new byte[],"");
        }



    }

    /***
     * md5密文
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String str = "abc";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        System.out.println(new String(byteArray,"UTF-8"));
        System.out.println(byteArray.toString());
    }

    @Test
    public void test3(){
        String s = "中文";
        byte[] b = s.getBytes();
        System.out.println(b.length);
        for(byte b1:b){
            System.out.println(b1);
        }

        System.out.println(new String(b));
    }


    static String base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i< Integer.MAX_VALUE; i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }


    @Test
    public void test4(){
        String s = "abc";
        String s1 = "abc";

        String s2 = new String("abc");
        String s3 = s2.intern();
        System.out.println(s == s1);
    }

    /**
     * 首次出现原则
     *java7 以后，intern 不会再实现复制，只是在常量池记录首次出现的实例引用
     * 所以intern()返回的引用和Stringbuilder创建的实例是同一个
     *
     * s = ab,常量池创建了ab，str2的intern()指向的是s，所以结果是false
     * */
    @Test
    public void test5(){
        //String listener = "计算机学习";
        String s = "ab";
        String str1 = new StringBuilder("计算机").append("学习").toString();
        System.out.println(str1.intern() == str1); //true

        String str2 = new StringBuilder("a").append("b").toString();
        System.out.println(str2.intern() == str2);//false
        System.out.println(s == str2.intern());//true

    }

}

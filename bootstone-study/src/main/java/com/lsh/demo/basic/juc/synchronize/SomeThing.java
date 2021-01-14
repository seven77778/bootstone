package com.lsh.demo.basic.juc.synchronize;

import java.util.regex.Pattern;

public class SomeThing {

    public static synchronized String staticAndSyncA() throws Exception{
        Thread.sleep(10000);
        System.out.println("static and synchronized A");
        return "static and synchronized A";
    }

    public static synchronized String staticAndSyncB() {
        System.out.println("static and synchronized B");
        return "static and synchronized B";
    }

    public synchronized String onlySyncA() {
        System.out.println("only synchronized A");
        return "only synchronized A";
    }

    public synchronized String onlySyncB() {
        System.out.println("only synchronized B");
        return "only synchronized B";
    }


    public static String onlyStatic() {
        System.out.println("only static");
        return "only static";
    }

    /*
    url ：https://databank.tmall.com/welcome
    正则：{pattern:/^(?=^.{3,255}$)(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\d+)*(\/\w+\.\w+)*([?&]\w+=\w*)*$/,message:'请输入正确网址'}

     */
    public static void main(String[] args) {
        String ext="/^(?=^.{3,255}$)(http(s)?:\\/\\/)?(www\\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\\d+)*(\\/\\w+\\.\\w+)*([?&]\\w+=\\w*)*$/";
        String str = "http://databank.tmall.com/welcome";
        boolean isMatch = Pattern.matches(ext, str);
        System.out.println(isMatch);

        String ext2 = "(https?|ftp|file):\\/\\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        boolean isMatch2 = Pattern.matches(ext2, str);
        System.out.println(isMatch2);
    }

}

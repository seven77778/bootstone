package com.lsh.demo.basic.system.jvm.basicjvm.load;

import org.junit.Test;

/**
 * Created by lsh on 2018/10/29.
 *
 * @author lsh
 * @date 2018/10/29
 *
 * 内部类是延时加载的，也就是说只会在第一次使用时加载。不使用就不加载，所以可以很好的实现单例模式
 *
 */
public class LoadTest {
    public static void main(String[] args) {
        //SuperClass[] superClasses = new SuperClass[5];
        //SuperConstant superConstant = new SuperConstant();
        System.out.println(SuperConstant.value);
     }

    public LoadTest() {
        System.out.println("outter");
    }

    static class Inner{

        private static String name = "inner name";

         public Inner() {
             System.out.println("inner");
         }
     }

     class Common{
        private String name = "common name";
         public Common() {
             System.out.println("common");
         }
     }
    /**
     * 静态内部类加载
     */
    @Test
    public void test(){
        new LoadTest();
        System.out.println( new Common().name);
    }
}

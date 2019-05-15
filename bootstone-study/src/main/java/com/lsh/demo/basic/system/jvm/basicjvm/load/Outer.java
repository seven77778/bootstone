package com.lsh.demo.basic.system.jvm.basicjvm.load;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/13 17:48.
 *
 * @author lsh
 * @date 2018/11/13
 *
 * @test 先构造一个类，然后在进行测试
 */
public class Outer {



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

    private  Outer() {
        System.out.println("outer");
    }

    /**
     * 执行一次
     * @param args
     */
    public static void main(String[] args) {
        new Outer();
    }

    /**
     * 执行两次构造方法 ？ test初始化加载一次？
     * yes
     */
    @Test
    public void test(){
        new Outer();
        new Outer();
    }
}

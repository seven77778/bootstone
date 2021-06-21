package com.lsh.demo.basic.system.jvm.basicjvm.memory;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by lsh on 2018/10/29.
 *
 * @author lsh
 * @date 2018/10/29
 */
public class MemoryTest {

    /**
     * vm options 配置的-Xms64m  -Xmx128m
     * -Xmx 最大内存
     * -Xms 最小内存
     * totalMemory 启动时加载xms参数
     * maxMemory 可以得到的最大的内存，取Xmx配置
     * */
    // TODO: 2018/10/29 修改了idea bin idea64.exe.vmoptions 未生效


    @Test
    public void test() throws Exception {
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().availableProcessors());
        Runtime.getRuntime().exec("calc");
    }

    /**
     * StackOverflow
     * no,最终是 java.lang.OutOfMemoryError: Java heap space
     * 堆内存不足
     */
    @Test
    public void testStackOverflow(){
        ArrayList list = Lists.newArrayList();
        for(;;){
            list.add(new Object());
        }
    }


    /**
     * StackOverflow
     * -- yes,递归调用直接报错
     */
    @Test
    public void testStackOverflow2(){

        for(;;){
            testStackOverflow2();
        }
    }



}

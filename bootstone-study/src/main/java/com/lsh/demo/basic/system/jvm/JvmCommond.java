package com.lsh.demo.basic.system.jvm;

import org.junit.Test;

/**
 * 1.查看jvm参数
 * ① jinfo -flags pid
 * 关于 jinfo的输出:
 *  Non-default VM flags:  会根据你配置的来自动修改
 *  Command line: 就是代码中配置的，以这个为准
 * @pic /jinfo-flags.png
 *
 *
 * ② jcmd pid VM.flags
 *
 * todo jvm中同时配了-XX:+UseParallelGC  -XX:+UseConcMarkSweepGC，最终哪个生效
 * -- 答案是无法启动
 *
 */
public class JvmCommond {

    /**
     * -XX:+PrintGCDetails -XX:+UseParallelGC  -XX:+UseConcMarkSweepGC
     *
     * -- Error: Could not create the Java Virtual Machine.
     * Error: A fatal exception has occurred. Program will exit.
     * 冲突了，直接报错，无法启动
     */
    @Test
    public void test(){
        FinalizeTest f = new FinalizeTest();
        f=null;
        System.gc();
    }
}

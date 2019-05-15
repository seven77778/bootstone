package com.lsh.demo.basic.system.gc;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/8 15:32.
 *
 * @author lsh
 * @date 2018/11/08
 *
 * eliminate  消除
 *
 * -XX:+DoEscapeAnalysis 开启逃逸分析
    -XX:+PrintEscapeAnalysis 开启逃逸分析后，可通过此参数查看分析结果
 */
public class LockEliminationTest {

    public void lockEliminate(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("1");
    }

    /**
     * 测试锁消除 -XX:EliminateLocks
     * ？？ java启动预热 ？？
     * 不带参数：
     * 消耗时间 ： 2996118
     消耗时间 ： 1339145
     消耗时间 ： 1406124
     消耗时间 ： 1383513
     消耗时间 ： 1283258
     *
     * 带参数：-XX:+EliminateLocks -XX:-UseBiasedLocking
     * 消耗时间 ： 15396114
     消耗时间 ： 998280
     消耗时间 ： 911677
     消耗时间 ： 526443
     消耗时间 ： 531562
     *
     * */
    @Test
    public void test(){
        for(int j=0;j<5;j++) {
            long time = System.nanoTime();
            LockEliminationTest eliminationTest = new LockEliminationTest();
            for (int i = 0; i < 10000; i++) {
                eliminationTest.lockEliminate();
            }
            System.out.println("消耗时间 ： " + (System.nanoTime() - time));
        }

    }

    /**
     * 锁消除，string的+号，在1.5之前是StringBuffer的append
     * 1.5之后，是Stringbuilder的append
     * */

    @Test
    public void test22(){
        System.out.println(getStr("a","b"));
        String a = "a";
        String b = "a";
        String c = a+b;

    }

    public String getStr(String str1, String str2){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str1);
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }


}

package com.lsh.demo.basic.system.jvm;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by lsh on 2018/11/5.
 *
 * @author lsh
 * @date 2018/11/05
 */
public class LocalGCTest {

    public static int _1mb = 20*1024;

    /**
     * -XX:+PrintGCDetails -Xmx100m -Xms100m  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\oomdum
     *
     * jinfo --  -XX:MaxNewSize=34603008  -XX:OldSize=70254592
     *堆内存100m，默认1:2，新生代34左右，老年代66
     * 后面s0，s1均为空。
     * 动态年龄对象判定--如果 Survivor相同年龄的对象总和，大于Survivor空间一半，大于等于该年龄的对象，直接进入老年代
     * 新生代打印的是新生代总空间的90%
     */
    @Test
    public void test() throws Exception {
        List list = Lists.newArrayList();
        for(int i=0;;i++) {
            byte[] b = new byte[_1mb];
            Thread.sleep(50);
            list.add(b);
        }
    }

    /**
     *内存使用原则：
     *1.早释放，晚申请，在马上就要用的时候在申请，最早释放，用完了就马上释放
     *2.少开辟
     *3.list,set,map等尽量给定capacity,resize或者rehashde的时候，都会废弃之前使用的那部分内存，
     * 把之前的对象重新组织一次。这个过程耗时，又浪费内存
     *4.减少字符串操作，字符串的contains，split，+，这些操作很常见，但是这些操作都是很没有效率的。
     * String对象是不可以修改的，有些操作很容易造成新string对象的浪费，或者耗时
     *
     * 字符串编码，我们程序中使用到了非常多的二字码(字母或数字)，三字码(字母)，我们使用short对这些二字码和三字码统一做了编码，压缩了存储，计算匹配的时候效率非常高。
     小对象map映射，计算中用到的数据，有很多简单的规则对象，这些对象比较简单，重复度非常高，所以我们在内存中做了一个映射，加载的时候使用同一个对象。降低了内存的占用，内存中的对象很稳定，GC非常友好。
     小数组二分查找，对于一些小的数组，在计算的时候需要使用contains，创建一个set有些浪费内存，使用数组的contains又对计算不友好。这个时候也许我们可以做一个均衡，数据使用排好序的数组来存储，contains的时候使用二分查找。
     bitSet，如果你要存一个1000，bitSet则会把第一千个bit置为1，适合当你要存储一堆取值范围不是很大的数值。计算匹配的效率很高，如果数值密度大，对内存压缩来说是非常高效的。bitSet还有一个黑魔法的用法，可以实现O(n)的排序，顺序输出就是排好序的结果，空间换时间。虽然局限性很大，但是如果场景适合，就会发现非常好用。
     缓存key：需要多个维度的数据合并为一个key的时候，通常会使用字符串拼接，但是字符串拼接很浪费内存，效率也不高，所以我们可以使用数值移位与操作来代替字符串的拼接，int+short+short = long。这些数值一部分可以通过字符串编码转换过来，一部分可以给内存对象生成唯一的ID key。
     有限种可能的枚举集合，思想类似于bitSet，我们可以使用int的每一个bit位来表示一种枚举类型的存在(1)或者不存在(0),就可以包含32组值，一个long可以包含64组，可以说信息量相当的大。
     */
    @Test
    public void test2(){
        //*****************早释放，晚申请
        Object[] objects = new Object[1024];
        for(Object o:objects){
            //业务操作 dododo
            o = null;

        }
        //*************************少开辟

        StringBuilder sb = new StringBuilder(1000);
        while(true){
            // doSomeThing(sb);
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }
}

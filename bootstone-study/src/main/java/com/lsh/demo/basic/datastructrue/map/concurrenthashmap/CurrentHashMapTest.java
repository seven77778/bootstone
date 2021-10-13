package com.lsh.demo.basic.datastructrue.map.concurrenthashmap;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lsh on 2018/11/14 17:54.
 *
 * @good first，load有list，exception，collection，reader
 *
 * @see ConcurrentHashMap#put(java.lang.Object, java.lang.Object)
 *
 * <p>
 * 源码解读
 * key 和 value 均不能为null
 * 拿到key的hashcode （高位与）
 * 定义volatile 的node数组 -- table，初始为null
 * int binCount --
 * 对一个for无限循环
 * 第一次，table为null，初始化 -- tab = initTable()
 * <p>
 * <p>
 * <p>
 * initTable()
 * sizeCtl 初始值，如果小于0，thread.yeild（）
 */
public class CurrentHashMapTest {

    @Test
    public void testGetHashCode(){
        String key="1233";
        System.out.println(new ConcurrentHashMap());
    }

    /**
     * >>> 的含义
     *
     * << : 左移运算符，num << 1,相当于num乘以2
     *
     * >> : 右移运算符，num >> 1,相当于num除以2
     *
     * >>> : 无符号右移，忽略符号位，空位都以0补齐
     *
     * 对于正数而言，>>和>>>没区别。
     */
    @Test
    public void testLeftThree() {
        //8 除以 2乘以2
        System.out.println(8 >>> 3);
        System.out.println(8 >> 3);
        System.out.println(8 / 9);
        System.out.println(-2 >>> 2);//1073741823

        int n = 9;
        n |= n >>> 1;
        int x = 9;
        x = x | x >>> 1;
        System.out.println(n);
        System.out.println(x);
        //复习  或运算 ,a 的值是129，转换成二进制就是10000001，而b 的值是128，转换成二进制就是10000000，根据或运算符的运算规律，只有两个位有一个是1，结果才是1，可以知道结果就是10000001，即129
        System.out.println(9|8);
    }


    @Test
    public void test() {
        Map<String, String> concurrentMap = Maps.newConcurrentMap();
        concurrentMap.put("a", "1");
        System.out.println(concurrentMap);
    }

    @Test
    public void test1(){
        Map<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("1","2");
        concurrentMap.get("1");
    }

    /**
     * 只有key不存在的时候，才能put成功
     * put成功，返回null，put失败返回已存在的value
     */
    @Test
    public void testPutIfAbsent(){
        Map map = new ConcurrentHashMap();
        map.put("1","2");
        Object rs = map.putIfAbsent("222", 3);
        Object rs2 = map.putIfAbsent("1", 333);
        System.out.println(rs2);
        System.out.println(rs);
        System.out.println(map);
    }

    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        map.put("1","2");
        System.out.println(map);

        Object rs = map.putIfAbsent("222", 3);
        System.out.println(rs);
        System.out.println(map);
    }
}

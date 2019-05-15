package com.lsh.demo.basic.datastructrue.map.weakhashmap;

import java.util.WeakHashMap;

/**
 * Created by lsh on 2018/11/27 17:18
 *
 * definition：WeakHashMapTest 线程不安全
 *
 * WeakHashMap实现了Map接口，使用弱引用作为内部数据的存储方案。
 * WeakHashMap是弱引用的典型应用，可以作为简单的缓存表解决方案。
 * WeakHashMap会在系统内存范围内，保存所有表项目，一旦内存不够
 * 在GC时，没有被引用的表项很快会被清除掉，从而避免系统内存溢出。
 */
public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMap<Integer,Integer> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(1,2);
        System.out.println(weakHashMap.get(1));
    }
}

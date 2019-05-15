package com.lsh.demo.basic.datastructrue.map.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lsh on 2019-04-29.
 *
 * 1.为什么hashmap线程不安全
 *
 * compare with CurrentHashMap,not safe because of resize ?
 *
 * node 是啥，又是自己类中写的
 * 1. int类型的hash
 * 2. 任意类型的k,v node<k,v> node实现map.entry
 * 3.一个包含全部field 的构造方法
 * 4.重写tostring  return key + "=" + value;
 * 5.重写 hashcode Objects.hashCode(key) ^ Objects.hashCode(value)
 *
 *
 *
 */
public class HashMapLearn {

    private static HashMap<String,String> hashMap = new HashMap<>(16);
    private static ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>(16);


    public static void main(String[] args) {

        hashMap.put("key","value");
        System.out.println(hashMap.get("key"));

    }



}

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
 *  因为多线程环境下，使用Hashmap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。
 *
 *  HashTable容器使用synchronized来保证线程安全，但在线程竞争激烈的情况下HashTable的效率非常低下。
 *  因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会进入阻塞或轮询状态。
 *  如线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用get方法来获取元素，所以竞争越激烈效率越低。
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

package com.lsh.demo.basic.datastructrue.map.hashmap;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lsh on 2019-05-15.
 *
 * 遍历map的几种方式
 *
 */
public class MapSort {

    private Map<String,String> map  = new HashMap<String,String>(16){
        private static final long serialVersionUID = -8986459655416580729L;
        {
            put("a","a1");
            put("b","b1");
        }
    };



    @Test
    public void test1(){
        map.forEach((x,y)-> System.out.println("key is "+x +" value is " + y));
    }

    @Test
    public void test2(){
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<String, String> next = iter.next();
            System.out.println(next.getKey() + next.getValue());
        }

        //替换为
        for (Map.Entry<String, String> next : map.entrySet()) {
            System.out.println(next.getKey() + next.getValue());
        }

        //keyset values
        for(String a : map.keySet()){
            System.out.println(a);
        }
    }

    /**
     * map 排序 error!
     */
    @Test
    public void test3(){
        Stream<Map.Entry<String, String>> result = map.entrySet().stream().sorted();
//        Map<String, String> sortMap = new LinkedHashMap<>();
//        map.entrySet().stream()
//                .sorted(Comparator.comparing(Map.Entry::getValue))
//                .forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));

        result.forEach(x-> System.out.println(x));
    }

}

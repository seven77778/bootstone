package com.lsh.demo.basic.datastructrue.map.hashmap;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created by lsh on 2020-06-08.
 *
 */
public class ClearAndnull {

    public static void main(String[] args) {
        HashMap<Integer,Integer> map = Maps.newHashMap();
        map.put(1,11);
        map.put(2,22);
        map.put(3,33);
        map.clear();
        System.out.println(map);
        map.put(5,55);
        System.out.println(map);
        map = null;
        System.out.println(map);
    }

}

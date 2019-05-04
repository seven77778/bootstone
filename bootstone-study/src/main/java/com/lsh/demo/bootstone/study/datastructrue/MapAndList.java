package com.lsh.demo.bootstone.study.datastructrue;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LSH on 2019/5/3 - 9:22.
 * <p>
 * declaration : 通过map.values 可以直接构造list
 */
public class MapAndList {

    public static void main(String[] args) {
        Map<String,String> map = Maps.newHashMap();

        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");

        List<String> list =new ArrayList<>(map.values());
        System.out.println(list);
        //[value1, value2, value3]
    }
}

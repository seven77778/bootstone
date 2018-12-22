package com.lsh.demo.bootstone.study.datastructrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lsh on 2018/12/12.
 *
 * 测试 hsahmap 和currentHashMap的clear方法
 */
public class MapClear {

    private static Map map = new HashMap<>();
    private static Map map1 = new ConcurrentHashMap();


    public static void main(String[] args) {
        map.clear();
    }

}

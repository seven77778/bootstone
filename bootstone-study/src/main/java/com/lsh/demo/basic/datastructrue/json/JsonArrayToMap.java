package com.lsh.demo.basic.datastructrue.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsh on 2020-06-05.
 */
public class JsonArrayToMap {


    public static void main(String[] args) {

        String str = "[\n" +
                "    {\n" +
                "        \"id\": \"123\",\n" +
                "        \"area\": \"444\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"123\",\n" +
                "        \"area\": \"xxx\"\n" +
                "    },\n";

        JSONArray array = JSONArray.parseArray(str);

        System.out.println(array);
         Map<String, String> hotelAddress = new HashMap<>();

        for(Object arr : array){
            hotelAddress.put( ((JSONObject)arr).getString("id"),((JSONObject)arr).getString("area"));
        }
        System.out.println(hotelAddress);
    }
}

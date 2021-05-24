package com.lsh.demo.basic.datastructrue.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

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


    @Test
    public void test(){
        String str="[\n" +
                "\t{\n" +
                "\t\t\"explain\": \"哈哈哈\",\n" +
                "\t\t\"key\": \"key111\",\n" +
                "\t\t\"paramStatus\": \"1\",\n" +
                "\t\t\"paramType\": \"2\",\n" +
                "\t\t\"required\": \"1\",\n" +
                "\t\t\"validationRule\": \"\",\n" +
                "\t\t\"value\": \"1111\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"explain\": \"哈哈哈\",\n" +
                "\t\t\"key\": \"key222\",\n" +
                "\t\t\"paramStatus\": \"1\",\n" +
                "\t\t\"paramType\": \"2\",\n" +
                "\t\t\"required\": \"1\",\n" +
                "\t\t\"validationRule\": \"\",\n" +
                "\t\t\"value\": \"222\"\n" +
                "\t}\n" +
                "\n" +
                "]";

        JSONArray array = JSONArray.parseArray(str);

        System.out.println(array);

        for(Object arr : array){
            if(((JSONObject)arr).getString("key").equals("key111")){
                ((JSONObject)arr).put("value","u1100111");
            }
        }
        System.out.println(array);
    }
}

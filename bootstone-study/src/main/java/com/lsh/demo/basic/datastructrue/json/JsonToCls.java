package com.lsh.demo.basic.datastructrue.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by lsh on 2020-06-05.
 */
public class JsonToCls {

    public static void main(String[] args) {
        String str = "[{\"id\":\"aaa\",\"area\":\"111\"},{\"id\":\"bbb\"," +
                "\"area\":\"222\"},{\"id\":\"333\",\"area\":\"aaa\"}" ;

        JSONArray array = JSONArray.parseArray(str);
        boolean flag = false;
        for(Object arr : array){
          if("lsh".equals (((JSONObject)arr).getString("key"))) {
              flag = "1".equals(((JSONObject) arr).getString("value"));
              break;
            }
        }
        System.out.println(flag);
    }

    /**
     * {
     *     "errno": 0,
     *     "errmsg": "",
     *     "statusCode": 200,
     *     "data": {
     *         "encryptData": "123",
     *         "macId": "aaa",
     *         "deviceName": "zzz"
     *     }
     * }
     */
    @Test
    public void test() {

        String str = "{\n" +
                "    \"errno\": 0,\n" +
                "    \"errmsg\": \"\",\n" +
                "    \"statusCode\": 200,\n" +
                "    \"data\": {\n" +
                "        \"accessToken\": {\n" +
                "            \"accessToken\": \"xxx=\",\n" +
                "            \"expire\": 2079\n" +
                "        }\n" +
                "    }\n" +
                "}";

        MakeResponse resultRs = JSON.parseObject(str, MakeResponse.class);
        System.out.println(resultRs);

        //字符串对应不上,com.alibaba.fastjson.JSONException: error parse false

        String str2 = "fdsfs/dfdsf!@#@$";
        MakeResponse resultRs2 = JSON.parseObject(str2, MakeResponse.class);
        System.out.println(resultRs2);


    }

}

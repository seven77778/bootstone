package com.lsh.demo.basic.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.junit.Test;

/**
 * Created by lsh on 2020-01-03.
 * <p>
 * json 转换为具体的类
 *
 * @JSONField(name = "message") 完美解决
 */
public class JsonToClass {

    @Test
    public void test() {

        String str = "{\"code\":\"500\",\"errorMessage\":\"成员未找到，请稍后重试\",\"errorCode\":\"member_not_find\",\"message\":\"成员未找到，请稍后重试\"}";
        HappyResponse response = JSON.parseObject(str, HappyResponse.class);
        System.out.println(response);
        //就不是json了
        String str2 = "{code:500,errorMessage:成员未找到，请稍后重试,errorCode:member_not_find,message:成员未找到，请稍后重试}";
//        System.out.println(JSON.parseObject(str2, HappyResponse.class));
    }

    /**
     * 转换为jsonobject 来取值
     */
    @Test
    public void test2(){
        String str = "{\"code\":\"500\",\"errorMessage\":\"成员未找到，请稍后重试\",\"errorCode\":\"member_not_find\",\"message\":\"成员未找到，请稍后重试\"}";
        JSONObject object = JSON.parseObject(str);
        System.out.println(object.getString("message"));
    }


    @Data
    public static class HappyResponse {
        private String code;
        private String errorMessage;
        private String errorCode;


        @JSONField(name = "message")
        private String massage;

        private String errDetailMsg;
        private boolean success=true;
    }

}

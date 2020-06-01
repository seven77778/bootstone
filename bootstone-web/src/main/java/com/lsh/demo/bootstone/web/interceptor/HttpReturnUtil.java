package com.lsh.demo.bootstone.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 2020-06-01.
 */
@Component
public class HttpReturnUtil {


    public static void returnJson(HttpServletResponse response, String errmsg) {
        String jsonMsg = new JSONObject()
                .fluentPut("success", false)
                .fluentPut("msg", errmsg)
                .toJSONString();
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            writer = response.getWriter();
            writer.print(jsonMsg);
        } catch (IOException e) {
            BootStoneLog.bootStone.error("response error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

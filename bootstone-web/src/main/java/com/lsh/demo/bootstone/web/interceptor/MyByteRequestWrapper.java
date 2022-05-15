package com.lsh.demo.bootstone.web.interceptor;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by lsh on 2020-06-03.
 * very ok
 *
 * 目的: 改变请求参数的值,满足项目需求(如:过滤请求中 lang != zh 的请求)
 *
 */
public class MyByteRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameterMap; // 所有参数的Map集合
    private  byte[] requestBody;
    //用来保存post的请求体，如果没有请求体，直接拦截
    private String originBody;

    public MyByteRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        //request.getInputStream() 调用多次依然正常 todo
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        originBody = new String(requestBody);
        //todo 修改请求入参 ,在fliter中修改
        parameterMap = request.getParameterMap();
        String json = new String(requestBody, "utf-8");
        BootStoneLog.bootStone.info("original request is "+json);
        String iii = "{\n" +
                "    \"lsh\":\"修改后的内容\"\n" +
                "}";
//        if(StringUtils.isNotBlank(originBody)){
//            requestBody = iii.getBytes();
//        }
        //现在return到postman的全是  修改后的内容  了
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.parameterMap;
    }

    public String getOriginBody() {
        return originBody;
    }


    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}

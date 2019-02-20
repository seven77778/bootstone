package com.lsh.demo.bootstone.web.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by lsh on 2019/2/12.
 */
@RestController
@Component
@RequestMapping("/hello")
public class HelloController {

    @Value("${valuetest.test1}")
    private String test1;

    @GetMapping("/test1212")
    public String test2(){
        System.out.println(test1);
        return test1;
    }

    /**
     * 入参的name为空，name.get()-java.util.NoSuchElementException: No value present
     */
    @GetMapping("get")
    public String getUser(String id, Optional<String> name){
        return "hello, "+name.get()+id;
    }

    @GetMapping("/test")
    public String test(HttpServletResponse httpServletResponse) throws Exception{
        httpServletResponse.setContentType("image/jpeg");
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        // 通过址默认配置创建一个httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpGet远程连接实例
        HttpGet httpGet = new HttpGet("http://all-daily.oss-cn-hangzhou.aliyuncs.com/jvopf/1961_c.jpg");
        // 设置请求头信息，鉴权
        httpGet.setHeader("Content-Type", "image/jpeg");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        // 执行get请求得到返回对象
        response = httpClient.execute(httpGet);
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        // 通过EntityUtils中的toString方法将结果转换为字符串
        result = EntityUtils.toString(entity);

        return null;
    }
}

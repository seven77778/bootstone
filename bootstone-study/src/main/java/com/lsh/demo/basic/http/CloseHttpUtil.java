package com.lsh.demo.basic.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsh on 2018/11/12 11:21.
 *
 * @author lsh
 * @date 2018/11/12
 *
 * 实现 CloseableHttpClient
 * 1.EntityUtils.toString 中finally 中关闭了 InputStream
 */
public class CloseHttpUtil {

    private static CloseableHttpClient closeableHttpClient;
    private static HttpClientBuilder httpClientBuilder;
    static final int CONNTECT_TIMEOUT = 1;
    static final int SOCKET_TIMEOUT = 1;

    static {
        httpClientBuilder = HttpClients.custom();
        closeableHttpClient = httpClientBuilder.build();
    }

    @Test
    public void test()throws Exception{
        HttpGet httpGetRequest = new HttpGet("http://www.baidu.com");
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(1000)//设置为1，connectime out
            .setSocketTimeout(1000)//设置为1，readtime out
            .setConnectionRequestTimeout(1)
            .build();
        httpGetRequest.setConfig(requestConfig);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGetRequest);
        try {
            // toString consume
            System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
    }

    @Test
    public void testPost()throws Exception{
        HttpPost post = new HttpPost("http://xxx/account/member/getOpenApiToken/v2");
        post.setHeader("Content-Type","application/json;charset=UTF-8");
        post.setHeader("Accept", "*/*");
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        //建立一个NameValuePair数组，用于存储欲传送的参数
        params.add(new BasicNameValuePair("orgCode","xxx"));
        params.add(new BasicNameValuePair("appKey","xxx"));
        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        CloseableHttpResponse response = closeableHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

    @Test
    public void testKXYA()throws Exception{
        HttpGet httpGetRequest = new HttpGet("http://localhost:8000?room=4545455");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置为1，connectime out
                .setSocketTimeout(10000)//设置为1，readtime out
                .setConnectionRequestTimeout(10000)
                .build();
        httpGetRequest.setConfig(requestConfig);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGetRequest);
        try {
            // toString consume
            System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            response.close();
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    @Test
    public void testhhh(){
        RestTemplate restTemplate = new RestTemplate();
        String url = new String("http://xxx/account/member/getOpenApiToken/v2");
        Map<String, String> body = new HashMap<>();
        body.put("orgCode", "xxx");
        body.put("appKey", "xxx");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        String result = restTemplate.postForObject(url, request, String.class);
        System.out.println("result : {}"+ result);
    }


}

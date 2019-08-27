package com.lsh.demo.basic.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

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

}

package com.lsh.demo.bootstone.web.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * @author lsh
 * @date 2022/3/26 11:26
 */
@Configuration
public class RestHttpConfig {

    @Value("${stone.read.time.out:1}")
    private Integer readTimeOut;
    @Value("${stone.connect.time.out:1}")
    private Integer connectTimeOut;
    @Value("${stone.connect.request.time.out:1}")
    private Integer connectRequestTimeOut;


    /**
     * 时间较长的接口配置
     */
    @Value("${stone.long.read.time.out:20000}")
    private Integer readLongTimeOut;
    @Value("${stone.long.connect.time.out:3000}")
    private Integer connectLongTimeOut;
    @Value("${stone.long.connect.request.time.out:3000}")
    private Integer connectLongRequestTimeOut;


    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //自定义
        List<MediaType> list = Lists.newArrayList();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        httpMessageConverter.setSupportedMediaTypes(list);
        httpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return httpMessageConverter;
    }

    /**
     * http连接管理器
     *
     * @return
     */
    @Bean
    public HttpClientConnectionManager poolingHttpClientConnectionManager() {
        /*// 注册http和https请求
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);*/

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(500);
        // 同路由并发数（每个主机的并发）
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        return poolingHttpClientConnectionManager;
    }

    /**
     * HttpClient
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置http连接管理器
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager());

        /*// 设置重试次数
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));*/

        // 设置默认请求头
        /*List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        httpClientBuilder.setDefaultHeaders(headers);*/

        return httpClientBuilder.setMaxConnTotal(128).setMaxConnPerRoute(16).build();
    }

    /**
     * 请求连接池配置
     *
     * @return
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        // httpClient创建器
        clientHttpRequestFactory.setHttpClient(httpClient());
        // 连接超时时间/毫秒（连接上服务器(握手成功)的时间，超出抛出connect timeout）
        clientHttpRequestFactory.setConnectTimeout(connectTimeOut);
        // 数据读取超时时间(socketTimeout)/毫秒（务器返回数据(response)的时间，超过抛出read timeout）
        clientHttpRequestFactory.setReadTimeout(readTimeOut);
        // 连接池获取请求连接的超时时间，不宜过长，必须设置/毫秒（超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool）
        clientHttpRequestFactory.setConnectionRequestTimeout(connectRequestTimeOut);
        return clientHttpRequestFactory;
    }


    /**
     * 接口超时设置较长时间的restTemplate
     *
     * @param httpClient
     * @return
     */
    @Bean
    public ClientHttpRequestFactory clientLongHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        clientHttpRequestFactory.setConnectTimeout(connectLongTimeOut);
        clientHttpRequestFactory.setReadTimeout(readLongTimeOut);
        clientHttpRequestFactory.setConnectionRequestTimeout(connectLongRequestTimeOut);
        return clientHttpRequestFactory;
    }

    /**
     * rest模板
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate(BootResponseErrorHandler bootResponseErrorHandler) {
        // boot中可使用RestTemplateBuilder.build创建
        RestTemplate restTemplate = new RestTemplate();
        // 配置请求工厂
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        restTemplate.setErrorHandler(bootResponseErrorHandler);


        return restTemplate;
    }

    /**
     * rest模板
     *
     * @return
     */
    @Bean
    public RestTemplate restLongTemplate(ClientHttpRequestFactory clientLongHttpRequestFactory,BootLogInterceptor bootLogInterceptor) {
        // boot中可使用RestTemplateBuilder.build创建
        RestTemplate restTemplate = new RestTemplate();
        // 配置请求工厂
        restTemplate.setRequestFactory(clientLongHttpRequestFactory);
        restTemplate.getInterceptors().add(bootLogInterceptor);
        return restTemplate;
    }


    @Component
    static class BootLogInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            //dosomething
            BootStoneLog.bootStone.info("开始处理http请求");
            BootStoneLog.bootStone.info("url ：{}", request.getURI());
            BootStoneLog.bootStone.info("method ：{}", request.getMethod());
            BootStoneLog.bootStone.info("body ：{}", new String(body,"utf-8"));
            ClientHttpResponse clientHttpResponse = execution.execute(request, body);
            return clientHttpResponse;
        }
    }

    @Component
    static class BootResponseErrorHandler implements ResponseErrorHandler{

        /**
         * 报错没打印 todo
         * @param response
         * @return
         * @throws IOException
         */
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            BootStoneLog.bootStone.info("this is hasError");
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            BootStoneLog.bootStone.info("this is handleError");
        }
    }

}

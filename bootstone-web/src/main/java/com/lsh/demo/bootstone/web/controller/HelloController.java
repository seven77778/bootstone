package com.lsh.demo.bootstone.web.controller;

import com.bootstone.spring.ioc.iocdemo1.MoAttack;
import com.lsh.demo.bootstone.service.vo.RequsetVO;
import com.lsh.demo.bootstone.web.common.request.BootStoneRequest;
import com.lsh.demo.bootstone.web.interceptor.LshAuth;
import com.lsh.demo.bootstone.web.point.MyWithinPoint;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by lsh on 2019/2/12.
 */
@RestController
@Component
@RequestMapping("/hello")
@ComponentScan(basePackages = "com.bootstone.spring.ioc.iocdemo1.MoAttack")
@CrossOrigin("*")
public class HelloController {

    @Value("${valuetest.test1}")
    private String test1;

    @Autowired
    private MoAttack moAttack;

    @RequestMapping("/hello")
    @LshAuth
    public String hello(String lsh){
        return lsh;
    }

    @RequestMapping("/hellopost")
    @LshAuth
    public String helloPost(@RequestBody BootStoneRequest rq){
        return rq.getLsh();
    }


    @PostMapping("/js")
    public String js(String username,String password){
        return "hello " + username + " " + password;
    }

    @GetMapping("/test2")
    public String test2(){
        this.moAttack.selectGeli();
        System.out.println("test2");
        System.out.println(test1);
        return "test2";
    }


    @GetMapping("/test1")
    @MyWithinPoint
    public String test22(){
        System.out.println("test1 @MyWithinPoint ");
        return "test1";
    }

    /**
     * springboot 完全ok
     */
    @PostConstruct
    public void test(){
        System.out.println("this is post Construct");
    }


    /**
     * 前端传参不能为空，可以是{}，否则
     * RuntimeExceptionInvokeorg.springframework.http.converter.HttpMessageNotReadableException:
     * Required request body is missing
     * @RequestBody(required = false) 可解
     * @param vo
     * @return
     */
    @RequestMapping("/body")
    public String testBody(@RequestBody RequsetVO vo){
        if(null==vo){
            return  "null";
        }
        return "ok";
    }

    @RequestMapping("/nullbody")
    public String testNullBody(@RequestBody(required = false) RequsetVO vo){
        if(null==vo){
            return  "null";
        }
        return "ok";
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

    public static void main(String[] args) {
        int [] ab = new int[] {0,0,0,1};

        long time1 = System.currentTimeMillis();
        for(int x : ab){
            if(0!=x){
                System.out.println("false");
            }
        }
        System.out.println(System.currentTimeMillis() - time1 );

        long time2 = System.currentTimeMillis();
        boolean result = Stream.of(ab).allMatch(x -> Arrays.equals(x, new int[0]));
        System.out.println(System.currentTimeMillis() - time2);

        long ss = Arrays.stream(ab).filter(x -> x != 0).count();
        System.out.println(ss);




    }
}

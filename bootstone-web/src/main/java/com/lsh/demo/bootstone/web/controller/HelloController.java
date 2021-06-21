package com.lsh.demo.bootstone.web.controller;

import com.bootstone.spring.ioc.iocdemo1.MoAttack;
import com.lsh.demo.bootstone.service.vo.RequsetVO;
import com.lsh.demo.bootstone.web.common.request.BootStoneRequest;
import com.lsh.demo.bootstone.web.interceptor.LshAuth;
import com.lsh.demo.bootstone.web.point.MyWithinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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

    /**
     * 测试spring的controller是单例还是多例，怎么保证并发的安全
     * @return
     */

    /**
     *1.  xhrFields: { //加了这个，后端返回的set-cookie，下次 同源 请求自动带cookie了
     *                     withCredentials: true
     *                 },
     *
     *                 前端加上这个，只要不跨越，就能带cookie
     *
     *  2. withCredentials: false ，+后端@cross注解，可以跨域，无cookie
     */
    @RequestMapping("/crossOrigin")
    public String crossOrigin(String param, HttpServletRequest request, HttpServletResponse response){
        Cookie[] getC = request.getCookies();
        System.out.println("拿到前端cookie-" + (getC ==null));
        Cookie cookie = new Cookie("cookie-key","cookie-value");
        response.addCookie(cookie); //有了这个，response header中就有了 Set-Cookie: cookie-key=cookie-value
        return param+ "crossOrigin";
    }

    @RequestMapping("/hello")
//    @LshAuth
    public String hello(String lsh){
        return "第222台";
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


    /**
     * 测试 @before 切面
     * @return
     */
    @GetMapping("/test1")
    @MyWithinPoint
    public String test22(){
        System.out.println("test1 @MyWithinPoint ");
        return "test1";
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
        return "ok~ " +vo.getName();
    }

    /**
     * {} 并不算请求body为null
     * 请求为null的进不来了
     */
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
        if(name.isPresent()) {
            return "hello, " + name.get() + id;
        }else {
            return "no name";
        }
    }

}

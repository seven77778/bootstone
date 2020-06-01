package com.lsh.demo.bootstone.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 2020-05-12.
 * <p>
 * http://localhost:8084/hello/hello
 * postman返回
 * "error": "Internal Server Error",
 * "message": "Java heap space",
 * 1、-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\oom -Xmx50m -Xms50m
 * 当时测试oom都设置为了50m
 * 2、在创建应答时，不小心递归调用了returnJosn方法，导致 java.lang.OutOfMemoryError: Java heap space
 * 3、WebAppConfigurer 需要添加配置，spring是xml - mvc:interceptors，boot实现WebMvcConfigurer
 * 4、还没成功，就是启动类没有扫描到 interceptor这个包
 * <p>
 * <p>
 * isAssignableFrom 是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的超类或接口。
 * 通常调用格式是
 * Class1.isAssignableFrom (Class2)
 * 调用者和参数都是   java.lang.Class   类型
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从http header获取token
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (!assignableFrom) {
            return true;
        }

        // 通过注解来判断
        LshAuth methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(LshAuth.class);
        if (methodAnnotation == null) {
            return true;
        }

        String token = request.getHeader("token");
        if (null == token || token.trim().equals("")) {
            token = request.getParameter("token");
        }
        if (null == token || token.trim().equals("")) {
            returnJson(response, "token is blank");
            return false;
        }
        //token存在，该怎么验证就怎么验证
        if ("lshtoken".equals(token)) {
            BootStoneLog.bootStone.info("auth success");
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BootStoneLog.bootStone.info("this is postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BootStoneLog.bootStone.info("this is afterCompletion");
    }


    private void returnJson(HttpServletResponse response, String errmsg) {
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

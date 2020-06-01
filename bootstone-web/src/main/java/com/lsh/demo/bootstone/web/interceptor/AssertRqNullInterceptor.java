package com.lsh.demo.bootstone.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lsh on 2020-06-01.
 *
 * 判断前端过来的请求是不是为null，有没有带{}body体
 *
 * httpServletRequest.getReader()只能被读取一次
 * httpServletRequest.getHeader() 多次？
 *
 * HttpMessageNotReadableException
 * org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: No content to map due to end-of-input; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException:
 * No content to map due to end-of-input
 *
 */
public class AssertRqNullInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);
//        if (!assignableFrom) {
//            return true;
//        }
//
//        if(!"POST".equals(httpServletRequest.getMethod())){
//            return true;
//        }
//
//        if(null==httpServletRequest.getReader().readLine()){
//            HttpReturnUtil.returnJson(httpServletResponse,"拦截了");
//            return false;
//        }
//        return true;

        MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) httpServletRequest);

        System.out.println(myRequestWrapper.getBody());
        System.out.println(myRequestWrapper.getBody());
        if(StringUtils.isBlank(myRequestWrapper.getBody())){
            HttpReturnUtil.returnJson(httpServletResponse,"拦截了");
            return false;
        }
        return true;

    }







}

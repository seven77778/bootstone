package com.lsh.demo.bootstone.web.filter;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import com.lsh.demo.bootstone.web.interceptor.MyByteRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lsh on 2020-06-01.
 */
public class ReplaceStreamFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest myByteRequestWrapper = null;
        if (request instanceof HttpServletRequest) {
//            myByteRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
            //会影响最终传递到controller的参数
            myByteRequestWrapper = new MyByteRequestWrapper((HttpServletRequest) request);
        }
        if (null == myByteRequestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(myByteRequestWrapper, response);
        }
        BootStoneLog.bootStone.info("replace stream success");
    }

    @Override
    public void destroy() {

    }
}

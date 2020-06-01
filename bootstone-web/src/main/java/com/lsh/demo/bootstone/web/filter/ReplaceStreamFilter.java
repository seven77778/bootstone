package com.lsh.demo.bootstone.web.filter;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import com.lsh.demo.bootstone.web.interceptor.MyRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lsh on 2020-06-01.
 */
public class ReplaceStreamFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest myRequestWrapper = null;
        if (request instanceof HttpServletRequest) {
            myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
        }

        if (null == myRequestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(myRequestWrapper, response);
        }
        BootStoneLog.bootStone.info("replace stream success");
    }

    @Override
    public void destroy() {

    }
}

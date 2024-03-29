package com.lsh.demo.bootstone.web.filter;

import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import com.lsh.demo.bootstone.web.interceptor.MyByteRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.btrace.core.annotations.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static org.openjdk.btrace.core.BTraceUtils.Strings.str;
import static org.openjdk.btrace.core.BTraceUtils.Strings.strcat;
import static org.openjdk.btrace.core.BTraceUtils.println;

/**
 * Created by lsh on 2020-06-01.
 * 解决HttpServletRequest的输入流只能读取一次的问题
 */


@BTrace
public class ReplaceStreamFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }




    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getInputStream();
        MyByteRequestWrapper myByteRequestWrapper = null;
        if (request instanceof HttpServletRequest) {
//            myByteRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
            //会影响最终传递到controller的参数
            myByteRequestWrapper = new MyByteRequestWrapper((HttpServletRequest) request);
        }

        //修改入参lsh中的value  localhost:8084/hello/hello?lsh=123
        if (null != myByteRequestWrapper && null != myByteRequestWrapper.getParameterMap()) {
            Map<String, String[]> parameterMap = myByteRequestWrapper.getParameterMap();
            if (null != parameterMap.get("lsh") && StringUtils.isNotBlank(parameterMap.get("lsh")[0])) {
                Map<String, String[]> changeMap = Maps.newHashMap();
                changeMap.put("lsh", new String[]{"already changed lsh"});
                myByteRequestWrapper.setParameterMap(changeMap);
                BootStoneLog.bootStone.info("replace stream success");
            }
        }
        chain.doFilter(myByteRequestWrapper, response);
        BootStoneLog.bootStone.info("request success");
    }

    @Override
    public void destroy() {

    }
}

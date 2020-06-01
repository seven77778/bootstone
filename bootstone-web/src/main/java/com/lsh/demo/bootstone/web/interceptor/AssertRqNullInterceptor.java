package com.lsh.demo.bootstone.web.interceptor;

import com.lsh.demo.bootstone.service.ErrorEnum;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
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
 * --body体为空的就拦截掉了，不继续走就OK了
 *
 * 查看InputStream的源码可知，读取流的时候会根据position来获取当前位置，并且随着读取来进行位置的移动。如果想要重新读取，可以调用inputstream.reset方法，但是能否reset取决于markSupported方法，返回true可以reset，反之不行。查看ServletInputStream可知，这个类并没有复写markSupported和reset方法，查看父类InputStream：
 *
 * @see java.io.InputStream
 * public boolean markSupported() {
 *         return false;
 *     }
 * public synchronized void reset() throws IOException {
 *         throw new IOException("mark/reset not supported");
 *     }
 *
 * 其他方案：1.@Validated 2.切面
 *
 */
public class AssertRqNullInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (!assignableFrom) {
            return true;
        }
        if(!"POST".equals(httpServletRequest.getMethod())){
            return true;
        }
        MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) httpServletRequest);

        System.out.println(myRequestWrapper.getBody());
        System.out.println(myRequestWrapper.getBody());
        if(StringUtils.isBlank(myRequestWrapper.getBody())){
            BootStoneLog.bootStone.info("请求体body为空，已被拦截");
            HttpReturnUtil.returnJson(httpServletResponse, ErrorEnum.REQUEST_BODY_NULL);
            return false;
        }
        return true;

    }







}

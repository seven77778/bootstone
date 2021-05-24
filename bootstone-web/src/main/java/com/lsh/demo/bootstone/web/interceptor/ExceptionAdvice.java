package com.lsh.demo.bootstone.web.interceptor;

import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Created by lsh on 2020-05-15.
 *
 * 异常统一处理 ，这是一种方式  1.@ControllerAdvice+@ExceptionHandler
 * 2.HandlerExceptionResolver 接口
 * @see HandlerExceptionResolver
 *
 * 全局处理错误码，可以使用切面
 */
@ControllerAdvice
public class ExceptionAdvice {


    /**
     * 事务回滚，手动抛出的异常，为了带出具体的错误
     *
     * @param e 操作异常
     * @return 返回结果
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public String RuntimeExceptionInvoke(RuntimeException e) {
        BootStoneLog.bootStone.error("Runtime异常统一处理************");
        return "RuntimeExceptionInvoke" + e.toString();

    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String ExceptionInvoke(RuntimeException e) {
        BootStoneLog.bootStone.error("Exception异常统一处理************");
        return "Exception" + e.toString();

    }

}

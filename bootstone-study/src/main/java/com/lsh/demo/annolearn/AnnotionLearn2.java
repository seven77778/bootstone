package com.lsh.demo.annolearn;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Created by lsh on 2020-05-20.
 *
 * @see ExceptionHandlerMethodResolver
 * @see ExceptionHandlerExceptionResolver
 */
public class AnnotionLearn2 {

    public static final ReflectionUtils.MethodFilter EXCEPTION_HANDLER_METHODS = method ->
            (AnnotationUtils.findAnnotation(method, MyAnNo.class) != null);


    //detectExceptionMappings 方法前面的at+圈圈
    private void testHasAt(){
        System.out.println(MyAnNo.class);
    }


}

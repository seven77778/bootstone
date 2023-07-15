package com.lsh.demo.annolearn.spi_test;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.stereotype.Component;

/**
 * @author lsh
 * @date 2023/4/19 12:14
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER, "lsh"})
@Component
public class MyActiveFilterImplA implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("this is my MyActiveFilterImpl");
        return null;
    }
}

package com.lsh.demo.annolearn.spi_test;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.stereotype.Component;

/**
 * @author lsh
 * @date 2023/4/19 12:14
 */
@Activate(group = "nrsc", order = 2)
@Component
public class MyActiveFilterImplB implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("this is my MyActiveFilterImplBBB");
        return null;
    }
}

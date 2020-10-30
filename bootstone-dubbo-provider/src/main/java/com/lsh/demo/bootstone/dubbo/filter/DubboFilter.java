package com.lsh.demo.bootstone.dubbo.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.*;

/**
 * dubbo的filter
 * 1.统一入口设置参数，方便维护代码
 * 2.解决一次请求，涉及多次调用RPC时获取不到上下文的情况
 */
@Activate(group = Constants.PROVIDER)
public class DubboFilter implements com.alibaba.dubbo.rpc.Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        if (StringUtils.isEmpty(traceId)) {
            System.out.println("filter拿到为空");
        } else {
            System.out.println("filter拿到：" + traceId);
        }


        return null;
    }
}

package com.lsh.demo.bootstone.workorder;

import com.lsh.demo.bootstone.dubbo.RegisterService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by lsh on 2019/2/13.
 * test
 * No provider available from registry 127.0.0.1:2181 for service
 * 一般就是提供者没有注册成功
 * 检查@service注解，@EnableDubbo(scanBasePackages 等
 */
@Component
public class ProjectOrderService {

    @Resource(name = "registerServiceImpl")
    private RegisterService registerService;

    public String give() throws IOException {


        /**
         * RpcContext 只能消费者第一次接收到能拿到值
         * @see ContextFilter
         */
        RpcContext.getContext().setAttachment("param", "first");
        RpcContext.getContext().setAttachment("traceId", "12121212");
        String res1 = registerService.register("aaa", 12);
        System.out.println("请求返回1-" + res1);
        RpcContext.getContext().setAttachment("param", "second");
        String res2 = registerService.register("aaa", 12);
        System.out.println("请求返回1-" + res2);
        return "11";

    }


}

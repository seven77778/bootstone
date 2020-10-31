package com.lsh.demo.bootstone.dubbo.impl;

import com.lsh.demo.bootstone.dubbo.RegisterService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lsh on 2018/12/8.
 *
 * VM 启动参数格式：
 * -Dubbo.registry.timeout=5000
 * 但是无效。。。
 *
 * dubbo启动检查失败
 * registry:
 *     check: false
 *     timeout: 10000
 * 都无效
 *
 */
@Component
public class RegisterServiceImpl implements RegisterService {

    @Override
    public String register(String name, Integer age) {
        Map<String, String> attachments = RpcContext.getContext()
                .getAttachments();
        String param = attachments.get("param");
        System.out.println("param：" + param);

        String param2 = attachments.get("param");
        System.out.println("第二次获取param：" + param2);
        return name + "-" + age;

    }


}

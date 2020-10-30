package com.lsh.demo.bootstone.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.lsh.demo.bootstone.dubbo.RegisterService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by lsh on 2018/12/8.
 */
@Component
@Service
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

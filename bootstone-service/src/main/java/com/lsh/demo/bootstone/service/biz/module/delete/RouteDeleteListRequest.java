package com.lsh.demo.bootstone.service.biz.module.delete;

import com.lsh.demo.bootstone.service.biz.vo.RouteBaseRequest;

public class RouteDeleteListRequest extends RouteBaseRequest {
    @Override
    public String getMethodName() {
        return "route-deleteByList";
    }
}

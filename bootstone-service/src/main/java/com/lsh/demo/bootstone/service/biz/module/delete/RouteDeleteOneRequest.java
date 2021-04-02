package com.lsh.demo.bootstone.service.biz.module.delete;

import com.lsh.demo.bootstone.service.biz.vo.RouteBaseRequest;

public class RouteDeleteOneRequest extends RouteBaseRequest {
    @Override
    public String getMethodName() {
        return "route-deleteByOne";
    }
}

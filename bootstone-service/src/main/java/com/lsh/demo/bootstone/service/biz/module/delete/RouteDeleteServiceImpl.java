package com.lsh.demo.bootstone.service.biz.module.delete;

import com.lsh.demo.bootstone.service.biz.anno.RouteApiName;
import org.springframework.stereotype.Service;

@Service
public class RouteDeleteServiceImpl implements RouteDeleteService {



    @Override
    @RouteApiName(name = "route-deleteByOne")
    public String deleteByOne(RouteDeleteOneRequest rq) {
        return "deleteByOne";
    }

    @Override
    @RouteApiName(name = "route-deleteByList")
    public String deleteByList(RouteDeleteListRequest rq) {
        return "deleteByList";
    }
}

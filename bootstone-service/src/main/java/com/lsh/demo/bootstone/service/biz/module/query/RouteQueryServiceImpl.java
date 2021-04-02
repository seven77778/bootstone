package com.lsh.demo.bootstone.service.biz.module.query;

import com.lsh.demo.bootstone.service.biz.anno.RouteApiName;
import org.springframework.stereotype.Service;

@Service
public class RouteQueryServiceImpl implements RouteQueryService {



    @Override
    @RouteApiName(name = "route-query")
    public String queryName(RouteQueryRequest rq) {
        return "queryName";
    }
}

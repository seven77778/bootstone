package com.lsh.demo.bootstone.service.biz.module.query;

import com.lsh.demo.bootstone.service.biz.module.route.RequestBeanRouteService;

public interface RouteQueryService extends RequestBeanRouteService {

    /**
     * 查询名称
     * @return
     */
    String queryName(RouteQueryRequest rq);

}

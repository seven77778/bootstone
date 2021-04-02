package com.lsh.demo.bootstone.service.biz.module.delete;

import com.lsh.demo.bootstone.service.biz.module.route.RequestBeanRouteService;

public interface RouteDeleteService extends RequestBeanRouteService {

    String deleteByOne(RouteDeleteOneRequest rq);
    String deleteByList(RouteDeleteListRequest rq);

}

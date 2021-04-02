package com.lsh.demo.bootstone.web.test;

import com.lsh.demo.bootstone.service.biz.module.delete.RouteDeleteListRequest;
import com.lsh.demo.bootstone.service.biz.module.out.RouteOutService;
import com.lsh.demo.bootstone.service.biz.module.query.RouteQueryRequest;
import com.lsh.demo.bootstone.service.biz.vo.NotImplRequest;
import org.junit.Test;

import javax.annotation.Resource;

public class RouteServiceTest extends BaseTest {

    @Resource
    private RouteOutService routeOutService;

    @Test
    public void test(){
        RouteQueryRequest rq = new RouteQueryRequest();
        String rs = routeOutService.doExcute(rq);
        System.out.println(rs);


        RouteDeleteListRequest deleteListRequest = new RouteDeleteListRequest();
        String deleteRs = routeOutService.doExcute(deleteListRequest);
        System.out.println(deleteRs);

        NotImplRequest notImplRequest = new NotImplRequest();
        String notRs = routeOutService.doExcute(notImplRequest);
        System.out.println(notRs);

    }
}

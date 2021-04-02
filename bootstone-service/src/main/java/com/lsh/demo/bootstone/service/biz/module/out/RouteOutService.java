package com.lsh.demo.bootstone.service.biz.module.out;

import com.lsh.demo.bootstone.service.biz.vo.RouteBaseRequest;

/**
 * 唯一的对外提供的接口
 */
public interface RouteOutService {

    String doExcute(RouteBaseRequest rq);

}

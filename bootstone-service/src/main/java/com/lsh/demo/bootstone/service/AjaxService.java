package com.lsh.demo.bootstone.service;

import com.lsh.demo.bootstone.service.domain.request.AjaxHotelRequest;
import com.lsh.demo.bootstone.service.domain.response.BootResponse;
import com.lsh.demo.bootstone.service.domain.vo.AjaxHotel;

/**
 * Created by lsh on 2020-06-15.
 */
public interface AjaxService {

    BootResponse<AjaxHotel> queryHotel(AjaxHotelRequest rq);

    BootResponse addHotel(AjaxHotelRequest rq);

}

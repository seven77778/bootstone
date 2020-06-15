package com.lsh.demo.bootstone.web.controller;

import com.lsh.demo.bootstone.service.AjaxService;
import com.lsh.demo.bootstone.service.domain.request.AjaxHotelRequest;
import com.lsh.demo.bootstone.service.domain.response.BootResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lsh on 2020-06-15.
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Resource
    private AjaxService ajaxService;

    @PostMapping("/queryhotel")
    public BootResponse queryhotel(@RequestBody AjaxHotelRequest rq){
        return ajaxService.queryHotel(rq);
    }

    @PostMapping("/addhotel")
    public BootResponse addhotel(@RequestBody AjaxHotelRequest rq){
        return ajaxService.addHotel(rq);
    }
}

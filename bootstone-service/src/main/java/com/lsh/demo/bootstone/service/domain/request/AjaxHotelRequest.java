package com.lsh.demo.bootstone.service.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by lsh on 2020-06-15.
 */
@Data
public class AjaxHotelRequest extends BootRequest {
    //酒店id
    private String id;
    //开始时间
    private LocalDateTime createTime;
    //酒店名称
    private String hotelName;
    //酒店电话
    private String tel;
    //酒店状态 1- 营业 0 -停业
    private Integer hotelStatus;

}

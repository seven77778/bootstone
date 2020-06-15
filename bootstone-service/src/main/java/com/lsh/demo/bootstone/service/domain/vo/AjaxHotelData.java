package com.lsh.demo.bootstone.service.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by lsh on 2020-06-15.
 */
@Data
public class AjaxHotelData {
    private String id;
    private LocalDateTime createTime;
    private String hotelName;
    private String tel;
    private Integer hotelStatus;


}

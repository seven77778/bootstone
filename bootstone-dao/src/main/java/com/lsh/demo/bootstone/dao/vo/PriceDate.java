package com.lsh.demo.bootstone.dao.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 具体到某一天的价格
 */
@Data
public class PriceDate {

    private LocalDate localBeginDate;
    private LocalDate localEndDate;
    private Date beginDate;
    private Date endDate;
    private Integer price;
    private String name;

}

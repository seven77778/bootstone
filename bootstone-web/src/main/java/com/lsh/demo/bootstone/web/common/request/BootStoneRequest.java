package com.lsh.demo.bootstone.web.common.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by lsh on 2020-08-04.
 */
@Data
public class BootStoneRequest {

    private String lsh;
    private String name;
    private Date date;
}

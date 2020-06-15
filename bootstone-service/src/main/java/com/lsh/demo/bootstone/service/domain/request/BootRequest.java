package com.lsh.demo.bootstone.service.domain.request;

import lombok.Data;

/**
 * Created by lsh on 2020-06-15.
 */
@Data
public class BootRequest {


    //分页大小 默认10
    private Integer currentSize;

    //当前页，默认第1页
    private Integer currentPage;

}

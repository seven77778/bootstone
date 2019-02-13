package com.lsh.demo.bootstone.domain.object;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by lsh on 2019/2/12.
 */
@Data
public class LogDO extends BaseDO {

    private Long id;
    private String name;
    private LocalDateTime time;

}

package com.lsh.demo.bootstone.domain.enums;

import lombok.Getter;

@Getter
public enum OperateType {

    /**
     * 操作类型
     */
    login(1,"login");

    Integer type;
    String result;

    OperateType(Integer type,String result){
        this.result=result;
        this.type=type;
    }

}

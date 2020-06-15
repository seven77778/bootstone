package com.lsh.demo.bootstone.service.domain.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lsh on 2020-06-15.
 */
@Data
public class BootResponse<T> implements Serializable {

    private Boolean success;
    private String msg;
    private String code;

    private T data;

    public static <T> BootResponse<T> ofSuccess(){
        BootResponse response = new BootResponse();
        response.setCode("0");
        response.setSuccess(true);
        return response;
    }

    public static <T> BootResponse<T> ofSuccess(T data){
        BootResponse response = new BootResponse();
        response.setCode("0");
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static BootResponse ofFailure(){
        BootResponse response = new BootResponse();
        response.setCode("1");
        response.setSuccess(false);
        return response;
    }

    public static BootResponse ofFailure(String msg){
        BootResponse response = new BootResponse();
        response.setCode("1");
        response.setMsg(msg);
        response.setSuccess(false);
        return response;
    }
}

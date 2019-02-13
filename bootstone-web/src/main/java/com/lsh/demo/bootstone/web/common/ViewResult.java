package com.lsh.demo.bootstone.web.common;

import com.alibaba.jv.framework.domain.ResultDO;
import com.fliggy.jvopf.domain.object.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by congzhe.wb on 2018/10/10.
 */
@Getter
@Setter
public class ViewResult<T> extends BaseDO {
    private T data;
    private boolean success = true;
    private String errorCode;
    private String errorMessage;


    public ViewResult(){
    }

    protected ViewResult(String errorCode, String message){
        this();
        setSuccess(false);
        setErrorCode(errorCode);
        setErrorMessage(message);
    }

   /* public ViewResult(ErrorEnum errorEnum, String... params){
        this(errorEnum.getCode(),String.format(errorEnum.getMessage(), params));
    }
    public static ViewResult fail(ErrorEnum errorEnum){
        return new ViewResult(errorEnum.getCode(),errorEnum.getMessage());
    }*/

    public static <T> ViewResult<T> success(T data){
        ViewResult<T> result = new ViewResult();
        result.setData(data);
        return result;
    }

    public static ViewResult fail(String errorCode,String message){
        return new ViewResult(errorCode,message);
    }



    public static ViewResult copyResult(ResultDO resultDO){
        ViewResult result = new ViewResult();
        result.setData(resultDO.getModule());
        result.setSuccess(resultDO.isSuccess());
        result.setErrorCode(resultDO.getErrCode());
        result.setErrorMessage(resultDO.getErrMsg());
        return result;
    }
}

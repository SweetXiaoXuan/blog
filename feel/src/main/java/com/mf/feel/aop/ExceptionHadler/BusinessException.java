package com.mf.feel.aop.ExceptionHadler;


import com.mf.feel.utils.JsonResult;
import lombok.Getter;

/**
 * 全局异常类
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private JsonResult.Code code;  //错误码
    private String message;

    public BusinessException(JsonResult.Code code) {
        super(code.getMessage());
        this.code = code;
        this.message = code.getMessage();
    }

    public BusinessException(JsonResult.Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
package com.mf.feel.aop.ExceptionHadler;

import com.mf.feel.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 捕获异常
 */
@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> JsonResult<?> defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        JsonResult<Object> jsonResult = new JsonResult<>();
        if (e instanceof BusinessException) {
            log.error("业务异常：" + e.getMessage(), this.getClass());
            BusinessException businessException = (BusinessException) e;
            jsonResult.setErrorResult(businessException.getCode(), businessException.getMessage());
            return jsonResult;
        }
        //未知错误
        jsonResult.setErrorResult(JsonResult.Code.SERVER_ERROR, "系统异常：" + e.getMessage());
        jsonResult.setResultBody(e.getCause());
        return jsonResult;
    }
}
	

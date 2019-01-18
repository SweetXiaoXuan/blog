package com.mf.feel.utils.validate;

import com.mf.feel.utils.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;


/**
 * 接口参数校验,分组校验的时候使用
 *
 * @author xurunfei
 * @since 2018/7/23 11:43
 */
@Component
@Aspect
@Order(10)
public class ValidateParamGroupImpl {
    @Pointcut(value = "@annotation(ValidateParamGroup) ")
    private void cut() {
    }

    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] object = point.getArgs();
        for (Object o : object) {
            if (o.getClass().equals(BeanPropertyBindingResult.class)) {
                BindingResult bindingResult = (BindingResult) o;
                if (bindingResult.hasErrors()) {
                    JsonResult<Object> jsonResult = new JsonResult<>();
                    Map<String, Object> validInfo = jsonResult.getValidInfo();
                    List<FieldError> fieldError = bindingResult.getFieldErrors();
                    for (FieldError fieldError2 : fieldError) {
                        validInfo.put(fieldError2.getField(), fieldError2.getDefaultMessage());
                        jsonResult.setErrorResult(JsonResult.Code.PARAM_ERROR, fieldError2.getDefaultMessage());
                    }
                    return jsonResult;
                }
            }
        }
        return point.proceed();
    }
}

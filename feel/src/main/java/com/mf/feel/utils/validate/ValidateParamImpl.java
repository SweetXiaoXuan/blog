package com.mf.feel.utils.validate;

import com.mf.feel.utils.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;


/**
 * 接口参数校验,可同时校验参数在方法上,而不是bean中的情况
 *
 * @author xurunfei
 * @since 2018/7/23 11:43
 */
@Component
@Aspect
@Order(10)
public class ValidateParamImpl {

    //    // 获取校验的工厂的信息
    private static final Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .buildValidatorFactory().getValidator();

    @Pointcut(value = "@annotation(ValidateParam) ")
    private void cut() {
    }

    /**
     * 参数校验,如果参数不正确,直接返回错误信息,不进入方法
     */
    @Around(value = "cut()")
    public Object before(ProceedingJoinPoint point) throws Throwable {
        //  获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object[] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);
        JsonResult jsonResult = ValidateConstraintViolationThrowExpection(validResult);
        //如果校验结果为参数错误,则直接放回错误信息
        if (jsonResult.getCode() == JsonResult.Code.PARAM_ERROR.getCode()) {
            return jsonResult;
        } else {
            return point.proceed();
        }
    }

    /**
     * 根据结果返回校验结果
     *
     * @param validResult 校验结果
     */
    private JsonResult ValidateConstraintViolationThrowExpection(Set<ConstraintViolation<Object>> validResult) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        if (!validResult.isEmpty()) {
            Map<String, Object> validInfo = jsonResult.getValidInfo();
            for (ConstraintViolation item : validResult) {
                //获取参数路径的信息(参数的位置，参数的名称等等)
                PathImpl path = (PathImpl) item.getPropertyPath();
                validInfo.put(path.getLeafNode().getName(), item.getMessage());
                jsonResult.setErrorResult(JsonResult.Code.PARAM_ERROR, item.getMessage());
            }
        }
        return jsonResult;
    }


    /**
     * 参数校验
     *
     * @param obj    校验目标
     * @param method 目标方法
     * @param params 目标参数
     * @return java.util.Set<javax.validation.ConstraintViolation
                    */
    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {

        ExecutableValidator validatorParam = validator.forExecutables();
        return validatorParam.validateParameters(obj, method, params);
    }

}

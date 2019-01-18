package com.mf.feel.utils.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 参数自动校验注解,分组校验的时候使用
 *
 * @author xurunfei
 * @since 2018/7/23 11:55
 */
@Target({ElementType.METHOD}) //方法级注解
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateParamGroup {
}

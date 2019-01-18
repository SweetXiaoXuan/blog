package com.mf.feel.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端交互返回的结果集
 *
 * @author xurunfei
 * @since 2018/7/23 11:59
 */
@Data
@NoArgsConstructor
public class JsonResult<T> implements Serializable {

    private boolean success;
    private int code;
    private String message = "";
    private T resultBody;
    /**
     * 验证字段消息
     */
    private Map<String, Object> validInfo;

    public JsonResult(T resultBody) {
        setSuccessResult(resultBody);
    }

    public Map<String, Object> getValidInfo() {
        if (validInfo == null) {
            validInfo = new HashMap<>();
        }
        return validInfo;
    }

    /**
     * 快速设置失败信息
     *
     * @param code    失败代码
     * @param message 错误消息
     * @see Code
     */
    public JsonResult<T> setErrorResult(Code code, String message) {
        this.success = false;
        this.message = message;
        this.code = code.getCode();
        return this;

    }

    /**
     * 快速设置失败信息(错误信息为错误代码的消息)
     *
     * @param code 失败代码
     * @see Code
     */
    public JsonResult<T> setErrorResult(Code code) {
        this.success = false;
        this.message = code.getMessage();
        this.code = code.getCode();
        return this;
    }

    /**
     * 快速设置成功消息
     *
     * @param resultBody 如果有结果集,返回结果
     */
    public JsonResult<T> setSuccessResult(T resultBody) {
        this.success = true;
        this.message = Code.SUCCESS.getMessage();
        this.code = Code.SUCCESS.getCode();
        this.resultBody = resultBody;
        return this;
    }

    /**
     * 快速设置成功消息(无结果集)
     */
    public JsonResult<T> setSuccessResult() {
        setSuccessResult(null);
        return this;
    }

    /**
     * 错误代码
     */
    public enum Code {
        SUCCESS(200, "请求成功"),
        ERROR(501, "失败"),
        SERVER_ERROR(500, "服务器错误"),
        PARAM_ERROR(40001, "参数错误"),
        LOGIN_EXPIRE(40002, "登录已过期"),
        USER_ERROR(40003, "账号错误"),
        USER_PWD_CHANGE_ERROR(40004, "请定期修改账号密码"),
        NO_PERMISSION(40005, "没有权限"),
        ;
        private Integer code;
        private String message;

        Code(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

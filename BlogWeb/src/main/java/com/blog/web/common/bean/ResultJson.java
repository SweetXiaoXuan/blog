package com.blog.web.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class ResultJson {
    private String code = OK;
    private Object body = null;
    private String msg = "成功";
    @JSONField(serialize = false, deserialize = false)
    public final static String OK = "0";
    @JSONField(serialize = false, deserialize = false)
    public final static String ERROR = "1";

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

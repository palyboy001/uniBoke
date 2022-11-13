package com.uniBoke.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求返回的消息类:
 *            message：消息类型
 *            code：消息代码
 *            data：消息内容
 *
 * */
//@Data
//@AllArgsConstructor
public class RequestMessage {
    private String message;
    private String code;
    private Object data;

    public RequestMessage(String message, String code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{"
                + "\"message\":\""
                + message + '\"'
                + ",\"code\":\""
                + code + '\"'
                + ",\"data\":"
                + data
                + "}";

    }
}

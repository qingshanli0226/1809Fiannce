package com.example.network.model;

import java.io.Serializable;

public class RegBean implements Serializable {

    /**
     * code : 200
     * message : 请求成功
     * result : 注册成功
     */

    private String code;
    private String message;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RegBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

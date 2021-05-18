package com.example.model;


public class GesturePasswordBean {
    /**
     * code : 200
     * message : 请求成功
     * result : 请求成功
     */

    private String code;
    private String message;
    private String result;

    @Override
    public String toString() {
        return "GesturePasswordBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

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

}
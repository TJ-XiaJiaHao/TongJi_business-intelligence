package com.bi.Domain;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public BaseResult() {
        this.code = 200;
        this.message = "successful";
    }

    public BaseResult(T data) {
        this.code = 200;
        this.message = "successful";
        this.data = data;
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
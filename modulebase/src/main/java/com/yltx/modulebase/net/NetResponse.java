package com.yltx.modulebase.net;

import java.io.Serializable;

public class NetResponse<T> implements Serializable {
    private int code;
    private String error;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "httpResponse{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", result=" + result +
                '}';
    }
}
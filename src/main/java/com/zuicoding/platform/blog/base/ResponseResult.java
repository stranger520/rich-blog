package com.zuicoding.platform.blog.base;

import java.io.Serializable;

/**
 * Created by Stephen.lin on 2017/8/30.
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 4077490404950864551L;
    private int code ;
    private boolean success = false;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(int code, boolean success) {
        this.code = code;
        this.success = success;
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(int code, boolean success, T data) {
        this.code = code;
        this.success = success;
        this.data = data;
    }

    public static ResponseResult success(){

        return new ResponseResult(200);
    }



    public int getCode() {
        return code;
    }

    public ResponseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}

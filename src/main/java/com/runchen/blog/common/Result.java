package com.runchen.blog.common;

import java.io.Serializable;

/**
 * 200 表示成功
 * 500 表示错误，错误信息在msg中
 * 501 bean验证错误，不管有多少个错误都以  map形式返回
 * 502 拦截器拦截到用户token出错
 * 555 异常抛出错误信息
 */
public class Result implements Serializable {

    protected static final long serialVersionUID = 1L;

    /** 数据 */
    private Object data;
    /** 消息 */
    private String msg;
    /** 状态码 */
    private Integer code;

    public Result() {
    }

    public Result(Object data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static Result ok() {
        return new Result(null, Constants.SUCCESS, 200);
    }

    public static Result ok(Object data) {
        return new Result(data, Constants.SUCCESS, 200);
    }

    public static Result errorMsg(String msg) {
        return new Result(null, msg, 500);
    }

    public static Result errorMap(Object data) {
        return new Result(data, Constants.ERROR, 501);
    }

    public static Result errorTokenMsg(String msg) {
        return new Result(null, msg, 502);
    }

    public static Result errorException(String msg) {
        return new Result(null, msg, 555);
    }

    public static Result errorUserQQ(String msg) {
        return new Result(null, msg, 556);
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setStatus(Integer code) {
        this.code = code;
        return this;
    }
}

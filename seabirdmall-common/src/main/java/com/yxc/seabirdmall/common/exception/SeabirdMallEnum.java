package com.yxc.seabirdmall.common.exception;

public enum SeabirdMallEnum {
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数校验异常");

    private int code;
    private String msg;

    SeabirdMallEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

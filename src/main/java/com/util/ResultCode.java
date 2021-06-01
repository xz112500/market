package com.util;

import lombok.Data;

public enum ResultCode {
    SUCCESS(true,200,"成功!"),
    ERROR(false,404,"失败!"),
    QUERYERROR(false,20081,"查询失败"),
    LOGIN_AUTH(false, 28004, "需要登录"),
    LOGIN_ACL(false, 28005, "没有权限"),
    LOGIN_ERROR(false, 23005, "登录失败"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    ;
    private boolean success;
    private String message;
    private int status;

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }



    @Override
    public String toString() {
        return "ResultCode{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }


    ResultCode(boolean success,int status ,String message) {
        this.success = success;
        this.message = message;
        this.status = status;
    }


}

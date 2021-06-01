package com.util;


import lombok.Data;


@Data
public class ResultUtil<T> {
    private boolean success;
    private Integer status;
    private String message;
    private T data;

    public  ResultUtil<T> ok(T t){
        ResultUtil<T> r=new ResultUtil<T>();
        r.setSuccess(ResultCode.SUCCESS.getSuccess());
        r.setStatus(ResultCode.SUCCESS.getStatus());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(t);
        return r;
    }
    public  ResultUtil<T> error(){
        ResultUtil<T> r=new ResultUtil<T>();
        r.setStatus(ResultCode.ERROR.getStatus());
        r.setMessage(ResultCode.ERROR.getMessage());
        r.setSuccess(ResultCode.ERROR.getSuccess());
        return r;
    }
/*    public ResultUtil data(String key, Object value){
        this.data.put(key, value);
        return this;
    }*/
/*    public ResultUtil data(Map<String,Object> map){
        this.setData(map);
        return this;
    }*/
/*    public ResultUtil meta(Map<String,Object> map){
        this.setMeta(map);
        return this;
    }
    public ResultUtil meta(String key,Object value){
        this.meta.put(key, value);
        return this;
    }*/
    public ResultUtil<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultUtil<T> status(int status){
        this.setStatus(status);
        return this;
    }
    public ResultUtil<T> success(boolean success){
        this.setSuccess(success);
        return this;
    }
}

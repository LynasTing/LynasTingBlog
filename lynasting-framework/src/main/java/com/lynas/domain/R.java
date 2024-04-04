package com.lynas.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lynas.enums.AppHttpCodeEnum;
import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R <T> implements Serializable {
  private Integer code;
  private String msg;
  private T data;
  public R() {
    this.code = AppHttpCodeEnum.SUCCESS.getCode();
    this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
  }
  public R(Integer code, T data) {
    this.code = code;
    this.data = data;
  }
  public R(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
  public R(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
  public static R errorResult(int code, String msg) {
    R result = new R();
    return result.error(code, msg);
  }
  public static R okResult() {
    R result = new R();
    return result;
  }
  public static R okResult(int code, String msg) {
    R result = new R();
    return result.ok(code, null, msg);
  }
  public static R okResult(Object data) {
    R result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS,
      AppHttpCodeEnum.SUCCESS.getMsg());
    if(data!=null) {
      result.setData(data);
    }
    return result;
  }
  public static R errorResult(AppHttpCodeEnum enums){
    return setAppHttpCodeEnum(enums,enums.getMsg());
  }
  public static R errorResult(AppHttpCodeEnum enums, String msg){
    return setAppHttpCodeEnum(enums,msg);
  }
  public static R setAppHttpCodeEnum(AppHttpCodeEnum enums){
    return okResult(enums.getCode(),enums.getMsg());
  }
  private static R setAppHttpCodeEnum(AppHttpCodeEnum enums,
                                                   String msg){
    return okResult(enums.getCode(),msg);
  }
  public R<?> error(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
    return this;
  }
  public R<?> ok(Integer code, T data) {
    this.code = code;
    this.data = data;
    return this;
  }
  public R<?> ok(Integer code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
    return this;
  }
  public R<?> ok(T data) {
    this.data = data;
    return this;
  }
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public T getData() {
    return data;
  }
  public void setData(T data) {
    this.data = data;
  }
}
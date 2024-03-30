package com.lynas.handler.exception;

import com.lynas.domain.ResponseResult;
import com.lynas.enums.AppHttpCodeEnum;
import com.lynas.excepion.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

// 如果只写@ControllerAdvice 处理当中的返回值，并不会直接放到响应体里面
// 需要再加上@ResponseBody 这两种注解还有复合注解 @RestControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

  @ExceptionHandler(SystemException.class)
  public ResponseResult systemExceptionHandler(SystemException e) {
    // 打印异常信息
    log.error("systemExceptionHandler + 出现了异常", e);

    // 从异常对象中获取提示信息返回封装
    return ResponseResult.errorResult(e.getCode(), e.getMsg());
  }

  @ExceptionHandler(Exception.class)
  public ResponseResult exceptionHandler(Exception e) {
    // 打印异常信息
    log.error("exceptionHandler + 出现了异常", e);

    // 从异常对象中获取提示信息返回封装
    return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
  }
}

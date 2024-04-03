package com.lynas.aspect;


import com.alibaba.fastjson.JSON;
import com.lynas.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogAspect {

  /**
   * 切面方法
   */
  @Pointcut("@annotation(com.lynas.annotation.SystemLog)")
  public void pt() {

  }

  /**
   * 通知方法(环绕)
   */
  @Around("pt()")
  public Object printAroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
    Object res;
    try {
      handleLogBefore(joinPoint);
      res = joinPoint.proceed();
      handleLogAfter(res);
    } finally {
      // 结束后换行
      log.info("=======End=======" + System.lineSeparator());
    }
    return res;
  }

  private void handleLogAfter(Object res) {
    // 打印出参
    log.info("Response : {}", JSON.toJSONString(res));
  }

  private void handleLogBefore(ProceedingJoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    // 获取被增强方法上的注解对象
    SystemLog annotation = getSystemLog(joinPoint);

    log.info("=======Start=======");
    // 打印请求 URL
    log.info("URL : {}", request.getRequestURL());
    // 打印描述信息
    log.info("business : {}", annotation);
    // 打印 Http method
    log.info("HTTP Method : {}", request.getMethod());
    // 打印调用 controller 的全路径以及执行方法
    log.info("Class Method : {}.{}", joinPoint.getSignature().getDeclaringType(), ((MethodSignature) joinPoint.getSignature()).getName());
    // 打印请求的 IP
    log.info("IP : {}", request.getRemoteHost());
    // 打印请求入参
    log.info("Request Args : {}", JSON.toJSON(joinPoint.getArgs()));
  }

  private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    SystemLog annotation = signature.getMethod().getAnnotation(SystemLog.class);
    return annotation;
  }
}

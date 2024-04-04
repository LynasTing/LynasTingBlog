package com.lynas;

/**
 * @Author LynasTing
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lynas.mapper")
public class  LynasTingAdminApplication {
  public static void main(String[] args) {
    SpringApplication.run(LynasTingAdminApplication.class, args);
    System.out.println("后台运行成功了！！！");
  }
}

package com.lynas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.lynas.mapper")
@EnableScheduling
public class LynasTingBlogApplication {
  public static void main(String[] args) {
    SpringApplication.run(LynasTingBlogApplication.class, args);
    System.out.println("成功了~！！！！！！");
  }
}

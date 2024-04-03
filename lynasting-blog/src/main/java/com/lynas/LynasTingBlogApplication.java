package com.lynas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.lynas.mapper")
@EnableScheduling
@EnableSwagger2
public class LynasTingBlogApplication {
  public static void main(String[] args) {
    SpringApplication.run(LynasTingBlogApplication.class, args);
    System.out.println("成功了~！！！！！！");
  }
}

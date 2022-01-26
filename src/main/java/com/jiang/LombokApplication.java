package com.jiang;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(basePackages = "com.jiang.mapper")
public class LombokApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(LombokApplication.class, args);
        boolean person = run.containsBean("person");
        System.out.println("person是否注入："+person);
    }

}

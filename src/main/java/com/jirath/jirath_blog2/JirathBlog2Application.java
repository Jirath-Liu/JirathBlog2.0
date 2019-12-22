package com.jirath.jirath_blog2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jirath.jirath_blog2.dao")
public class JirathBlog2Application {

    public static void main(String[] args) {
        SpringApplication.run(JirathBlog2Application.class, args);
    }

}

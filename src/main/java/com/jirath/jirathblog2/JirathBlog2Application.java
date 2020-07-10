package com.jirath.jirathblog2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@MapperScan("com.jirath.jirathblog2.dao")
public class JirathBlog2Application {

    public static void main(String[] args) {
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","[]");
        SpringApplication.run(JirathBlog2Application.class, args);
    }

}

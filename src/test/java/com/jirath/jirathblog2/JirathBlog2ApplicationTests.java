package com.jirath.jirathblog2;

import com.jirath.jirathblog2.controller.BlogContentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JirathBlog2ApplicationTests {
@Autowired
    BlogContentController blogContentController;
    @Test
    void contextLoads() {
        System.out.println(blogContentController.getPsgById(1));
    }

}

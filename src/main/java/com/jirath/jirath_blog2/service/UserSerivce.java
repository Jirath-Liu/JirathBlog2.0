package com.jirath.jirath_blog2.service;

import com.jirath.jirath_blog2.pojo.User;
import org.springframework.stereotype.Service;

public interface UserSerivce {
    User getInfoByAccount(String account);
}

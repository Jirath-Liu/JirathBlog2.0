package com.jirath.jirathblog2.service.impl;


import com.jirath.jirathblog2.enums.RedisKeyEnum;
import com.jirath.jirathblog2.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 每次请求时，为主页面，就加一。
     * 从缓存取，只对缓存操作，这里Redis在存储过程中使用了泛型，将保留参数中的类型，可以使用Integer
     * @return
     */
    @Override
    public Long getVisitTimes() {
        Long vt=(Long)redisTemplate.opsForValue().get(RedisKeyEnum.visitNum.getKey());
        redisTemplate.opsForValue().set(RedisKeyEnum.visitNum.getKey(),new Long(vt.longValue()+1));
        return vt;
    }

    @Override
    public String getStartTime() {
        LocalDateTime time=(LocalDateTime) redisTemplate.opsForValue().get(RedisKeyEnum.startTime.getKey());
        StringBuffer buffer=new StringBuffer();
        buffer.append(time.getYear());
        buffer.append("/");
        buffer.append(time.getMonth());
        buffer.append("/");
        buffer.append(time.getDayOfMonth());
        buffer.append(" ");
        buffer.append(time.getHour());
        buffer.append(":");
        buffer.append(time.getMinute());
        buffer.append(":");
        buffer.append(time.getSecond());
        return buffer.toString();
    }
}

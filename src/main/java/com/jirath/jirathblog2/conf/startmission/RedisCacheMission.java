package com.jirath.jirathblog2.conf.startmission;

import com.jirath.jirathblog2.dao.SystemDao;
import com.jirath.jirathblog2.enums.RedisKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
@Component
@Slf4j
public class RedisCacheMission implements ApplicationRunner {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SystemDao systemDao;
    /**
     * 会在服务启动完成后立即执行,从数据库中取出日期，访问次数，存入缓存
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化任务，执行系统信息Redis存储");
        //设置两个缓存
        LocalDateTime now = LocalDateTime.now();
        systemDao.updateStartTimes(now);
        redisTemplate.opsForValue().set(RedisKeyEnum.startTime.getKey(),now);
        redisTemplate.opsForValue().set(RedisKeyEnum.visitNum.getKey(),systemDao.getVisitTimes());
    }
}

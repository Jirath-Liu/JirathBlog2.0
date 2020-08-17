package com.jirath.jirathblog2.conf.startmission;

import com.jirath.jirathblog2.dao.SystemDao;
import com.jirath.jirathblog2.enums.RedisKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
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
@Order
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
        //因为设置了版本号，不需要再考虑刷新缓存
        // cleanCache();
        //设置两个缓存
        LocalDateTime now = LocalDateTime.now();
        systemDao.updateStartTimes(now);
        redisTemplate.opsForValue().set(RedisKeyEnum.startTime.getKey(),now);
        redisTemplate.opsForValue().set(RedisKeyEnum.visitNum.getKey(),systemDao.getVisitTimes());
        log.info("初始化完成");
    }

    @CacheEvict(cacheNames = {"blog","tag","column"},allEntries = true)
    public void cleanCache(){
        log.info("清空缓存");
    }
}

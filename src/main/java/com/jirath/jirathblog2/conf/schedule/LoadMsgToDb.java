package com.jirath.jirathblog2.conf.schedule;

import com.jirath.jirathblog2.dao.SystemDao;
import com.jirath.jirathblog2.enums.RedisKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author Jirath
 * @date 2020/5/7
 * @description:
 */
@Component
public class LoadMsgToDb {
    private final Logger logger = LoggerFactory.getLogger(LoadMsgToDb.class);
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SystemDao systemDao;
    /**
     * 每天凌晨4点更新，此时使用人数最少，压力最小
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void loadVisitedTime(){
        logger.info("================================");
        logger.info("系统定时更新信息");
        Long times= (Long) redisTemplate.opsForValue().get(RedisKeyEnum.visitNum.getKey());
        Long dbTimes=systemDao.getVisitTimes();
        logger.info("Redis中次数："+times+" db中次数："+dbTimes);
        if (times==null||times<dbTimes) logger.error("缓存访问次数异常！，请检查redis缓存");
        else systemDao.updateVisitTimes(times);
        logger.info("===========更新完成==============");

    }
}

package com.jirath.jirathblog2.service.impl;

import com.jirath.jirathblog2.dao.ShareDao;
import com.jirath.jirathblog2.pojo.Share;
import com.jirath.jirathblog2.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/30
 * @description:
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Cacheable(cacheNames = "share" ,key = "'all'")
    @Override
    public List<Share> getAll() {
        return shareDao.getAll();
    }

    @CacheEvict(cacheNames = "share",key = "'all'")
    @Override
    public void updateMsg(Share share) {
        Assert.notNull(share.getId(),"更新分享id不能为空");
        shareDao.updateMsg(share);
    }

    @CacheEvict(cacheNames = "share",key = "'all'")
    @Override
    public void del(Integer id) {
        shareDao.del(id);
    }

    @CacheEvict(cacheNames = "share",key = "'all'")
    @Override
    public void add(Share share) {
        shareDao.add(share);
    }
}

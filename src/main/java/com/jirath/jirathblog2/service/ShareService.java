package com.jirath.jirathblog2.service;

import com.jirath.jirathblog2.pojo.Share;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/30
 * @description:
 */
public interface ShareService {
    List<Share> getAll();
    void updateMsg(Share share);
    void del(Integer id);
    void add(Share share);
}

package com.jirath.jirathblog2.dao;

import com.jirath.jirathblog2.pojo.Share;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jirath
 * @date 2020/6/30
 * @description:
 */
@Repository
public interface ShareDao {
    @Select("SELECT id,name,url FROM share")
    List<Share> getAll();
    @Update("UPDATE share SET name=#{name},url=#{url} WHERE id = #{id}")
    void updateMsg(Share share);
    @Delete("DELETE FROM share WHERE id =#{value}")
    void del(Integer id);
    @Insert("INSERT INTO share (name,url) values(#{name},#{url})")
    void add(Share share);
}

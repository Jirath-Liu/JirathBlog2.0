package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.dao.UserIdentityDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jirath
 */
@RequestMapping("/test")
@Controller
public class Test {
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    UserIdentityDao userIdentityDao;
    @RequestMapping("test")
    @ResponseBody
    public Object userIdentities(){
        Subject subject= SecurityUtils.getSubject();
        return subject.hasRole("user");
    }
}

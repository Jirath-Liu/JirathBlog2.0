package com.jirath.jirath_blog2.controller;

import com.jirath.jirath_blog2.conf.shiro.MsgValueUtil;
import com.jirath.jirath_blog2.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
    @Autowired
    MsgValueUtil msgValueUtil;
    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestParam String account,@RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken = new UsernamePasswordToken(account, password);
        try {
            subject.login(passwordToken);
        }catch (UnknownAccountException e){
            System.out.println("无"+account);
            return "无账号";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误"+password);
            return "密码错误";
        }
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("login")
                .data(subject.getPrincipal())
                .build();
    }
    @RequestMapping("/unLogin")
    @ResponseBody
    public Object unLogin() {
        Subject subject;
        try {
            subject = SecurityUtils.getSubject();
            subject.logout();
        }catch (Exception e){
            return ResultVo.builder()
                    .code(msgValueUtil.getDefaultError())
                    .msg("error")
                    .data(null)
                    .build();
        }
        return ResultVo.builder()
                .code(msgValueUtil.getSuccess())
                .msg("unLogin")
                .data("success")
                .build();
    }
}

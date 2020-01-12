package com.jirath.jirathblog2.controller;

import com.jirath.jirathblog2.exception.ExistedAccountException;
import com.jirath.jirathblog2.service.ColumnService;
import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.pojo.User;
import com.jirath.jirathblog2.service.UserService;
import com.jirath.jirathblog2.vo.ResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础操作，无需登录
 * @author Jirath
 */
@Controller
public class UserBasicController {
    @Autowired
    UserService userService;
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    ColumnService columnService;
    @RequestMapping("/loginAccount")
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
    @ResponseBody
    @RequestMapping("/newAccount")
    public Object newAccount(User user){
        try {
            userService.insertUser(user);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("register")
                    .build();
        }catch (ExistedAccountException e){
            return ResultVo.builder()
                    .code(msgValueUtil.getExistAccount())
                    .msg("account existed")
                    .build();
        }

    }
}
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                      //
////////////////////////////////////////////////////////////////////
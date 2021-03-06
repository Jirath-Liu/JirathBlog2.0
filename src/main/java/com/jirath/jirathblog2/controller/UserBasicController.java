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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基础操作，无需登录
 * 只能登录管理员,不再允许新建用户
 * @author Jirath
 */
@ResponseBody
@Controller
public class UserBasicController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
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
            return ResultVo.builder()
                    .code(msgValueUtil.getNoAccount())
                    .msg("loginFailed")
                    .data(subject.getPrincipal())
                    .build();
        }catch (IncorrectCredentialsException e){
            logger.error("密码错误",e);
            System.out.println("密码错误"+password);
            return ResultVo.builder()
                    .code(msgValueUtil.getPasswordError())
                    .msg("loginFailed")
                    .data(subject.getPrincipal())
                    .build();
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
            logger.error("注销异常",e);
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
    //@ResponseBody
    @RequestMapping("/newAccount")
    @Deprecated
    public Object newAccount(User user){
        try {
            userService.insertUser(user);
            return ResultVo.builder()
                    .code(msgValueUtil.getSuccess())
                    .msg("register")
                    .build();
        }catch (ExistedAccountException e){
            logger.error("创建账户异常",e);
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
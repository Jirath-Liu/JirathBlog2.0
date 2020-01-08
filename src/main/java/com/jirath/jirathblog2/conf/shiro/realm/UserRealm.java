package com.jirath.jirathblog2.conf.shiro.realm;

import com.jirath.jirathblog2.util.MsgValueUtil;
import com.jirath.jirathblog2.pojo.User;
import com.jirath.jirathblog2.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jirath
 */
public class UserRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${shiro-salt}")
    String saltSetting;
    @Value("${shiro-role}")
    List<String> roles;
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account= principalCollection.getPrimaryPrincipal().toString();
        int userIdentity= userService.getIdentityById(Integer.valueOf(account));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> rolesSet=new HashSet<>();
        for(int i=0;i<=userIdentity;i++){
            rolesSet.add(roles.get(i));
        }
        logger.info("验证授权:"+account+" 权限:"+rolesSet);
        info.setRoles(rolesSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken passwordToken= (UsernamePasswordToken) authenticationToken;
        String account=passwordToken.getPrincipal().toString();
        String password=passwordToken.getPassword().toString();
        User userInfo= userService.getInfoByAccount(account);
        if (userInfo==null){
            throw new UnknownAccountException();
        }
        //自定义盐值,配置文件中配置
        ByteSource salt = ByteSource.Util.bytes(saltSetting);
        //因为系统识别用户使用的是数据库唯一标识，即userId,这里将用户登录后，获取的UserId作为principal（当事人）传入
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //安全数据
                userInfo.getUserId(),
                //密码
                userInfo.getUserPassword(),
                salt,
                getName()
        );
        logger.info("用户登录:"+userInfo.getBasicMsg());
        return authenticationInfo;
    }
}

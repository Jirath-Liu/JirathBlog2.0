package com.jirath.jirath_blog2.conf.shiro.Realm;

import com.jirath.jirath_blog2.conf.shiro.MsgValueUtil;
import com.jirath.jirath_blog2.pojo.User;
import com.jirath.jirath_blog2.service.UserSerivce;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Value("${shiro-salt}")
    String saltSetting;
    @Value("${shiro-role}")
    List<String> roles;
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    UserSerivce userSerivce;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account= principalCollection.getPrimaryPrincipal().toString();
        int userIdentity=userSerivce.getIdentityById(Integer.valueOf(account));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> rolesSet=new HashSet<>();
        rolesSet.add(roles.get(userIdentity));
        info.setRoles(rolesSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken passwordToken= (UsernamePasswordToken) authenticationToken;
        String account=passwordToken.getPrincipal().toString();
        String password=passwordToken.getPassword().toString();
        User userInfo=userSerivce.getInfoByAccount(account);
        System.out.println(account+"\n"+userInfo);
        if (userInfo==null){
            throw new UnknownAccountException();
        }
        //自定义盐值,配置文件中配置
        ByteSource salt = ByteSource.Util.bytes("jirath");

        //数据库也必须是加密过的！！！
        //这里因为数据库未加密，将数据库结果加密一下
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //原密码
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, userInfo.getUserPassword(), ByteSource.Util.bytes(salt), hashInteractions).toHex();
        System.out.println(result);
        //因为系统识别用户使用的是数据库唯一标识，即userId,这里将用户登录后，获取的UserId作为principal（当事人）传入
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUserId(),//安全数据
                result,//密码
                salt,
                getName()
        );
        return authenticationInfo;
    }
}

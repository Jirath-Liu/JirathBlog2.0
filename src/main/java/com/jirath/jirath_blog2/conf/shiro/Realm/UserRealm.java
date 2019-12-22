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

public class UserRealm extends AuthorizingRealm {
    @Value("shiro-salt")
    String saltSetting;
    @Autowired
    MsgValueUtil msgValueUtil;
    @Autowired
    UserSerivce userSerivce;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String account= (String) principalCollection.getPrimaryPrincipal();
        System.out.println(account);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(null);
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
        ByteSource salt = ByteSource.Util.bytes(account);

        //数据库也必须是加密过的！！！
        //这里因为数据库未加密，将数据库结果加密一下
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //原密码
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, userInfo.getUserPassword(), ByteSource.Util.bytes(salt), hashInteractions).toHex();
        System.out.println(result);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUserId(),//安全数据
                result,//密码
                salt,
                getName()
        );
        return authenticationInfo;
    }
}

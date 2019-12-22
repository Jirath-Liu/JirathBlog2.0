package com.jirath.jirath_blog2.conf.shiro;

import com.jirath.jirath_blog2.conf.shiro.Realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean
    public UserRealm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        UserRealm userRealm = new UserRealm();
        //设置加密方式
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式
        credentialsMatcher.setHashAlgorithmName("MD5");
        System.out.println("加密方式：MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //此处的设置，true加密用的hex编码，false用的base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/test/*","authc");
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

}
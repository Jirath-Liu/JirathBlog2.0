package com.jirath.jirathblog2.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShiroEncryptUtil {
    @Value("${shiro-salt}")
    String saltSetting;
    public String encrypt(String string){
        //自定义盐值,配置文件中配置
        ByteSource salt = ByteSource.Util.bytes(saltSetting);
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = SystemSettingUtil.shiroHashInteractions;
        String result = new SimpleHash(hashAlgorithmName, string, ByteSource.Util.bytes(salt), hashInteractions).toHex();
        return result;
    }
}

package com.jirath.jirath_blog2.conf.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("msg")
@Data
public class MsgValueUtil {
    private int success;

    private int existAccount;

    private int unLogin;
    private int noPower;
    private int noAccount;
    private int passwordError;
    private int defaultError;
}
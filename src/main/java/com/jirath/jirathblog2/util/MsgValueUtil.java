package com.jirath.jirathblog2.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jirath
 */
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
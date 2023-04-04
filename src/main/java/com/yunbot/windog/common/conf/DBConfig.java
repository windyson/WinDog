package com.yunbot.windog.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author fuce
 */
@Component
@ConfigurationProperties("spring.datasource.druid.master")
public class DBConfig {
    private static String url;
    private static String username;
    private static String password;
    private static String ip;
    private static String port;

    public static String getUrl() {
        return url;
    }

    public void setUrl(String str) {
        url = str;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String str) {
        username = str;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String str) {
        password = str;
    }

    public static String getIp() {
        return ip;
    }

    public void setIp(String str) {
        ip = str;
    }

    public static String getPort() {
        return url;
    }

    public void setPort(String str) {
        url = str;
    }

}

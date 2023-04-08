package com.yunbot.windog.common.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 * 
 * @author fuce
 */
@Component
@ConfigurationProperties(prefix = "fuce")
public class V2Config
{
    /** 项目名称 */
    private String name;
    /** 版本 */
    private String version;
    /** 版权年份 */
    private String copyrightYear;
    /** 上传路径 */
    private static String defaultBaseDir;
    /** 是否开启 上传static **/
    private static String isstatic;
    /** 开启存放静态文件夹后目录 **/
    private static String isroot_dir;
	/**
	 * 邮箱发送smtp
	 */
	private static String email_smtp;
	/**
	 * 发送邮箱端口
	 */
	private static String email_port;
	/**
	 * 发送邮箱登录账号
	 */
	private static String email_account;
	/**
	 * 发送邮箱登录密码
	 */
	private static String email_password;
	/**
	 * 收件人
	 */
	private static String email_receiver;
    /** 演示模式 **/
    private static String demoEnabled;
    /** 滚动验证码 **/
    private static Boolean rollVerification;
    private static String token;
    private static String wxrobot;

	/** 令牌 **/
	public static String getWxRobot() {
		return wxrobot;
	}

	public void setWxRobot(String str) {
		this.wxrobot = str;
	}

	/** 令牌 **/
    public static String getToken() {
    	return token;
	}

	public void setToken(String str) {
    	this.token = str;
	}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        this.copyrightYear = copyrightYear;
    }
    

	public static String getDefaultBaseDir() {
		return defaultBaseDir;
	}

	public  void setDefaultBaseDir(String defaultBaseDir) {
		V2Config.defaultBaseDir = defaultBaseDir;
	}

	public static String getIsstatic() {
		return isstatic;
	}

	public  void setIsstatic(String isstatic) {
		V2Config.isstatic = isstatic;
	}

	public static String getIsroot_dir() {
		return isroot_dir;
	}

	public void setIsroot_dir(String isroot_dir) {
		V2Config.isroot_dir = isroot_dir;
	}

	public static String getEmail_smtp() {
		return email_smtp;
	}

	public static void setEmail_smtp(String email_smtp) {
		V2Config.email_smtp = email_smtp;
	}

	public static String getEmail_port() {
		return email_port;
	}

	public static void setEmail_port(String email_port) {
		V2Config.email_port = email_port;
	}

	public static String getEmail_account() {
		return email_account;
	}

	public static void setEmail_account(String email_account) {
		V2Config.email_account = email_account;
	}

	/////add by william 2021-7-23
	public static String getEmail_receiver() {
		return email_receiver;
	}

	public static void setEmail_receiver(String email_receiver) {
		V2Config.email_receiver = email_receiver;
	}

	//2021-4-30 by william
	//密文解密
	public static String getEmail_password() {
//		V2Config.email_password = sm4Decode(email_password);
		return email_password;
	}

	//2021-4-30 by william
	//加密
	public static void setEmail_password(String email_password) {
//		V2Config.email_password = sm4Encode(email_password);
		V2Config.email_password = email_password;
	}
	public static String getDemoEnabled() {
		return demoEnabled;
	}

	public void setDemoEnabled(String demoEnabled) {
		V2Config.demoEnabled = demoEnabled;
	}

	public static Boolean getRollVerification() {
		return rollVerification;
	}

	public void setRollVerification(Boolean rollVerification) {
		V2Config.rollVerification = rollVerification;
	}
	
	
	
}

package com.yunbot.windog.shiro.config;

import com.yunbot.windog.shiro.service.MyShiroRealm;
import com.yunbot.windog.shiro.service.SM3CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;


/**
 * 权限配置文件
 * @ClassName: ShiroConfiguration
 * @author fuce
 * @date 2018年8月25日
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * 这是shiro的大管家，相当于mybatis里的SqlSessionFactoryBean
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//登录
		shiroFilterFactoryBean.setLoginUrl("/admin/login");
		//首页
		shiroFilterFactoryBean.setSuccessUrl("/");
		//错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
		//页面权限控制
		shiroFilterFactoryBean.setFilterChainDefinitionMap(ShiroFilterMapFactory.shiroFilterMap());

		shiroFilterFactoryBean.setSecurityManager(securityManager);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * web应用管理配置
	 * @param shiroRealm
	 * @param cacheManager
	 * @param manager
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(Realm shiroRealm,CacheManager cacheManager,RememberMeManager manager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setCacheManager(cacheManager);
		securityManager.setRememberMeManager(manager);//记住Cookie
		securityManager.setRealm(shiroRealm);
		securityManager.setSessionManager(sessionManager());
//		by william
//		ThreadContext.bind(securityManager);
		return securityManager;
	}
	/**
	 * session过期控制
	 *
	 * @return
	 * @author fuce
	 * @Date 2019年11月2日 下午12:49:49
	 */
	@Bean
	public  DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		// 设置session过期时间3600s
		Long timeout = 60L * 10000 * 60;//毫秒级别
		defaultWebSessionManager.setGlobalSessionTimeout(timeout);
		return defaultWebSessionManager;
	}

	/**
	 * 加密算法
	 *
	 * @return
	 */
//	@Bean
//	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName("MD5");//采用MD5 进行加密
//		hashedCredentialsMatcher.setHashIterations(1);//加密次数
//		return hashedCredentialsMatcher;
//	}

	/**
	 * by william 2021-4-26
	 * 上面的代码块注释了，替换成国密sm3的加密验证，密码变成64个加密字符了
	 * 原生代码采用md5的算法，该代码主要是实现用sm3替代md5
	 */
	@Bean
	public SM3CredentialsMatcher sm4CredentialsMatcher() {
		SM3CredentialsMatcher sm3CredentialsMatcher = new SM3CredentialsMatcher();
		return sm3CredentialsMatcher;
	}

	/**
	 * 记住我的配置
	 *
	 * @return
	 */
	@Bean
	public RememberMeManager rememberMeManager() {
		Cookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);//通过js脚本将无法读取到cookie信息
        cookie.setMaxAge(60 * 60 * 24);//cookie保存一天
		CookieRememberMeManager manager=new CookieRememberMeManager();
		manager.setCookie(cookie);
		return manager;
	}

	/**
	 * 缓存配置
	 *
	 * @return
	 */
	@Bean
	public CacheManager cacheManager() {
		MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();//使用内存缓存
		return cacheManager;
	}

//	/**
//	 * 配置realm，用于认证和授权
//	 * @param hashedCredentialsMatcher
//	 * @return
//	 */
//	@Bean
//	public AuthorizingRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
//		MyShiroRealm shiroRealm = new MyShiroRealm();
//		//校验密码用到的算法
//		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//		return shiroRealm;
//	}

	/**
	 * 配置realm，用于认证和授权
	 * by william 2021-4-26
	 * 上面的代码块注释了，替换成国密sm3的加密验证，密码变成64个加密字符了
	 * 原生代码采用md5的算法，该代码主要是实现用sm3替代md5
	 *
	 * @param sm3CredentialsMatcher
	 * @return
	 */
	@Bean
	public AuthorizingRealm shiroRealm(SM3CredentialsMatcher sm3CredentialsMatcher) {
		MyShiroRealm shiroRealm = new MyShiroRealm();
		//校验密码用到的算法
		shiroRealm.setCredentialsMatcher(sm3CredentialsMatcher);
		return shiroRealm;
	}
	
	/**
	 * 启用shiro方言，这样能在页面上使用shiro标签
	 * @return
	 */
	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
	
	/**
     * 启用shiro注解
     *加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    

}

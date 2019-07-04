package com.vinzor.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import com.vinzor.shiro.MyRealm;
import com.vinzor.shiro.RedisCacheManager;
import com.vinzor.shiro.RedisSessionDao;

@AutoConfigureBefore(RedisConfig.class)
@Configuration
public class ShiroConfig {	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	/**
	   * shiro管理生命周期的东西
	   * @return
	   */
	  @Bean
 	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	    return new LifecycleBeanPostProcessor();
	  }
	  /**
	   * @author nidaye
	   * 开启注解
	   */
	  @Bean
	    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
	        return new DefaultAdvisorAutoProxyCreator();
	    }
	/**
	 * 配置Realm为自己的Realm并且将凭证匹配器注入
	 * 表示密码进行一次md5加密
	 */
	@Bean
	public MyRealm getMyRealm() {
		MyRealm myRealm=new MyRealm();
		myRealm.setCacheManager(cacheManager());
		//myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myRealm;
	}
	/**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;)
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    /**
     * 
     * @param securityManager
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //配置访问权限 拦截器
       
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/static/**", "anon");//表示可以匿名访问
       // filterChainDefinitionMap.put("/", "anon"); 
        filterChainDefinitionMap.put("/index", "anon"); 
        filterChainDefinitionMap.put("/login", "anon"); 
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/register","anon");
        filterChainDefinitionMap.put("/403","anon");
        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        shiroFilterFactoryBean.setLoginUrl("/check");
        return shiroFilterFactoryBean;
    }
    /**
     *  安全管理模块，所有的manager在此配置
     */
    @Bean
    public SecurityManager securityManager() {
    	DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
    	securityManager.setRealm(getMyRealm());
    	securityManager.setSessionManager(sessionManager());
    	securityManager.setCacheManager(cacheManager());
    	//securityManager.setRememberMeManager(rememberMeManager());  
    	return securityManager;
    }
    
    @Bean
	public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();    
        return redisCacheManager;
    }
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setGlobalSessionTimeout(300);
        sessionManager.setDeleteInvalidSessions(true);//删除过期的session	
        return sessionManager;
    }
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    @Bean
    public RedisSessionDao redisSessionDAO() {
        RedisSessionDao redisSessionDAO = new RedisSessionDao();
        return redisSessionDAO;
    }   

    /**
     * 开启shiro aop注解支持 使用代理方式所以需要开启代码支持
     *  一定要写入上面advisorAutoProxyCreator（）自动代理。不然AOP注解不会生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 这个参数是RememberMecookie的名称，随便起。
     * remenberMeCookie是一个实现了将用户名保存在客户端的一个cookie，与登陆时的cookie是两个simpleCookie。
     * 登陆时会根据权限去匹配，如是user权限，则不会先去认证模块认证，而是先去搜索cookie中是否有rememberMeCookie，
     * 如果存在该cookie，则可以绕过认证模块，直接寻找授权模块获取角色权限信息。
     * 如果权限是authc,则仍会跳转到登陆页面去进行登陆认证.
     * @return
     */
    public SimpleCookie rememberMeCookie() {
      SimpleCookie simpleCookie = new SimpleCookie("remenbermeCookie");
      //<!-- 记住我cookie生效时间30天 ,单位秒;-->
      simpleCookie.setMaxAge(60);
      return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     */
    public CookieRememberMeManager rememberMeManager() {
      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
      cookieRememberMeManager.setCookie(rememberMeCookie());
      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
      cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
      return cookieRememberMeManager;
    }

    
}

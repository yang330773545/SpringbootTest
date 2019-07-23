package com.vinzor.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

//import com.vinzor.filter.MyFilter;
import com.vinzor.service.WebUserDetailsService;

//@Configuration
//@EnableWebSecurity
//启用注解
//@EnableGlobalMethodSecurity会报错
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebUserDetailsService webUserDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()//所有路径登录后可访问
				.and()
			.formLogin()
				.loginPage("/login") //登录页路径
				.defaultSuccessUrl("/")
				.permitAll() //允许所有人访问
				.failureUrl("/login/error") //失败url
				.and()
			.logout().permitAll()
				.and()
			.rememberMe()
				.tokenValiditySeconds(60)//有效时间
				.userDetailsService(webUserDetailsService);
		http.csrf().disable();//关闭跨域
       
		// 在 UsernamePasswordAuthenticationFilter 前添加 
      //  http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
        // 在 CsrfFilter 后添加 
     //   http.addFilterAfter(new MyFilter(), CsrfFilter.class);
	}
	/*
	 //Security的角色为ROLE_XXX 权限则无ROLE前缀
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //按顺序执行                                                               
				.antMatchers("/resources/**", "/signup", "/about").permitAll()   //任何用户都可以访问的 如静态资源               
				.antMatchers("/admin/**").hasRole("ADMIN")      //有ROLE_ADMIN权限的才可以访问这些路径   这里是hasROLE                              
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") //同时有可访问           
				.anyRequest().authenticated() //尚未匹配的任何URL要求用户进行身份验证                                                  
				.and()
			// ...
			.formLogin();
	}
	**/
	/*
		protected void configure(HttpSecurity http) throws Exception {
			http
				.logout()                                   //注销支持                             
					.logoutUrl("/my/logout")                //路径 启用CSRF（默认就启用了） 则为post                                 
					.logoutSuccessUrl("/my/index")          //注销成功后的页面                                
					.logoutSuccessHandler(logoutSuccessHandler)     //设置定制的 LogoutSuccessHandler。如果指定了这个选项那么logoutSuccessUrl()的设置会被忽略                         
					.invalidateHttpSession(true)            //注销后让Session无效                                  
					.addLogoutHandler(logoutHandler)          //设置自定义的注销处理方式. 默认SecurityContextLogoutHandler会被添加。                               
					.deleteCookies(cookieNamesToClear)          //允许指定在注销成功时将移除的cookie。这是一个现实的添加一个CookieClearingLogoutHandler的快捷方式                             
					.and()
				...
		}
	 */
	/*
	 创建多个 order先运行
	@Configuration
	@Order(1)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http
				.antMatcher("/api/**")                               
				.authorizeRequests()
					.anyRequest().hasRole("ADMIN")
					.and()
				.httpBasic();
		}
	}

	@Configuration                                                   
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.anyRequest().authenticated()
					.and()
				.formLogin();
		}
	}
	*/
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("{noop}password").roles("USER").and() //{noop}不使用加密  默认要使用加密 这样可以不使用
				.withUser("admin").password("{noop}admin123").roles("USER","ADMIN");
	}
	*/
		
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(); //默认值为10 在4到31之间  第二个参数SecureRandom random要使用的安全随机实例
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(webUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
}

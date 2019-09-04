package com.vinzor.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
//开启注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	      http
	          .authorizeRequests()  //定义哪些url需要保护，哪些url不需要保护
	              .antMatchers("/", "/message/").permitAll()    //定义不需要认证就可以访问
	              .anyRequest().authenticated()
	              .and()
	          .formLogin()
	              .loginPage("/login")  //定义当需要用户登录时候，转到的登录页面  post到login username passworld
	              .permitAll()
	              .and()
	          .logout()
	              .permitAll();
	      http.csrf().disable();
	  }
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      auth
	          .inMemoryAuthentication()
	              .withUser("user").password("{noop}password").roles("USER");
	     //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
	  }
	  
	  //cookie name 从 SESSIONID变为 JSESSIONID
	  //如果我们的当前域名是moe.cnkirito.moe，该正则会将Cookie设置在父域cnkirito.moe中，如果有另一个相同父域的子域名blog.cnkirito.moe也会识别这个Cookie，便可以很方便的实现同父域下的单点登录
	  @Bean
	  public CookieSerializer cookieSerializer() {
	      DefaultCookieSerializer serializer = new DefaultCookieSerializer();
	      serializer.setCookieName("JSESSIONID");
	      serializer.setCookiePath("/");
	      serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
	      return serializer;
	  }
}

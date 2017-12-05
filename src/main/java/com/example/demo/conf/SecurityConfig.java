package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 新建SecurityConfig类并继承WebSecurityConfigurerAdapter，
 * WebSecurityConfigurerAdapter是security提供用于更改默认配置，实现configure方法
 * @author YXB
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/**
	 * 定义认证用户信息获取来源，密码校验规则等
	 */  
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//inMemoryAuthentication 从内存中获取  
		//这里为了方便演示用户信息是放在内存中，实际项目中使用数据库验证可以使用jdbc也可以实现userDetailsService类。
        auth.inMemoryAuthentication().withUser("test").password("123456").roles("USER")
        	.and().withUser("admin").password("123456").roles("ADMIN");    
          
        //jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构  
        //usersByUsernameQuery 指定查询用户SQL  
        //authoritiesByUsernameQuery 指定查询权限SQL  
        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);  
          
        //注入userDetailsService，需要实现userDetailsService接口  
        //auth.userDetailsService(userDetailsService);  
	}
	
	/**
	 * 定义安全策略
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		 http.authorizeRequests()//配置安全策略  
         .antMatchers("/","/test").permitAll()//定义/请求不需要验证  
         .anyRequest().authenticated()//其余的所有请求都需要验证  
         .and()  
	     .logout()  
	         .permitAll()//定义logout不需要验证  
	         .and()  
	     .formLogin();//使用form表单登录  
	}

}

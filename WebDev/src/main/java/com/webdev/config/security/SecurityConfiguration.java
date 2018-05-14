package com.webdev.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
		
			
		//USER Role
			.antMatchers("/orders/**").hasRole("USER")
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/login/reset").hasRole("USER")
			
		//ADMIN Role
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/delete").hasRole("ADMIN")
			
		//All public URLS
			.antMatchers("/").permitAll()
			.antMatchers("/home").permitAll()
			.antMatchers("/menu").permitAll()
			
		//FORM BASED LOGIN 
			.and().formLogin()
					.loginPage("/login").permitAll()
			.and().logout().logoutSuccessUrl("/")
			.and().csrf();
		
			
	}
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authBuilder,DataSource dataSource) throws Exception{
		authBuilder.jdbcAuthentication().dataSource(dataSource)
		.rolePrefix("ROLE_")
		.usersByUsernameQuery("select user_id, password, enabled from Users where user_id = ?")
		.authoritiesByUsernameQuery("select user_id, role from user_role where user_id = ?");
	}
}

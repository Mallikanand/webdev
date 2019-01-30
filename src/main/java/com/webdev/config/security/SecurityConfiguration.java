package com.webdev.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CompositeFilter;

import com.webdev.controllers.LoginController;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	
	@Autowired
	private LoginController loginController; 
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
//		http.cors().disable();

		http.cors().and().authorizeRequests()
			//.antMatchers("/orders/**").hasRole("USER")
			.antMatchers("/user/**").hasRole("USER")
			//.antMatchers("/menu").permitAll()
			.antMatchers("/","/login","/webjars/**").permitAll()
//			.and().formLogin()
//			.loginPage("/login").permitAll()
			//FORM BASED LOGIN 
			.and().logout().addLogoutHandler(loginController::logout).logoutSuccessUrl("/");
			//.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
				  //.addFilter(new CORSEnablingFilter());
			
		
		/*
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
			.and().csrf();*/
		
			
	}
/*
  private Filter ssoFilter(){
	  
	  CompositeFilter filter = new CompositeFilter();
	  List<Filter> filters = new ArrayList<>();
	  filters.add(new CORSEnablingFilter());
	  filters.add(ssoFilter(fb(),"/login/facebook"));
	  filters.add(ssoFilter(github(),"/login/github"));
	  filter.setFilters(filters);
	  return filter; 
	  
  }
*/
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authBuilder,DataSource dataSource) throws Exception{
		authBuilder.jdbcAuthentication().dataSource(dataSource)
		.rolePrefix("ROLE_")
		.usersByUsernameQuery("select user_id, password, enabled from Users where user_id = ?")
		.authoritiesByUsernameQuery("select user_id, role from user_role where user_id = ?");
	}
/*
	  private Filter ssoFilter(ClientResources client, String path){
		  OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		  OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(),oAuth2ClientContext);
		  filter.setRestTemplate(template);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getReource().getUserInfoUri(), client.getClient().getClientId());
		  tokenServices.setRestTemplate(template);
		  filter.setTokenServices(tokenServices );
		  
		  return filter;

	  }
	  
	  @Bean
	  @ConfigurationProperties("facebook")
	  public ClientResources fb(){
		  return new ClientResources();
	  }

	  @Bean
	  @ConfigurationProperties("github")
	  public ClientResources github(){
		  return new ClientResources();
	  }

	  @Bean
	  public FilterRegistrationBean<Filter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter){
		  FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		  registration.setFilter(filter);
		  registration.setOrder(-100);
		  return registration;
	  }*/
}
/*
class CORSEnablingFilter implements Filter , InitializingBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = ((HttpServletResponse)response);
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Headers", "*");
		
		chain.doFilter(request, response);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		
	}
}

class ClientResources{
	
	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client;
	
	@NestedConfigurationProperty
	private ResourceServerProperties resource;
	
	public ResourceServerProperties getResource() {
		return resource;
	}

	public void setResource(ResourceServerProperties resource) {
		this.resource = resource;
	}

	public AuthorizationCodeResourceDetails getClient(){
		return client;
	}
	
	public ResourceServerProperties getReource(){
		return resource;
	}

	public void setClient(AuthorizationCodeResourceDetails client) {
		this.client = client;
	}
	

}
*/
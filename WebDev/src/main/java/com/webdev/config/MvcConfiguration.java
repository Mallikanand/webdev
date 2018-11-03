package com.webdev.config;

import com.webdev.converters.StringToMenuItemConverter;
import com.webdev.data.model.dto.Basket;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
// @EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/*
	 * @Override public void configureViewResolvers(ViewResolverRegistry
	 * registry) { registry.viewResolver(getViewResolver()); };
	 */

	@Bean
	public ViewResolver getViewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToMenuItemConverter());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/orders/**").allowedOrigins("http://localhost:3000");
		registry.addMapping("/login/**").allowedOrigins("http://localhost:3000");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
		gsonHttpMessageConverter.canRead(Basket.class, MediaType.APPLICATION_JSON);
		gsonHttpMessageConverter.canWrite(Basket.class, MediaType.APPLICATION_JSON);
		converters.add(gsonHttpMessageConverter);
	}
}

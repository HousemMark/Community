package com.xq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.xq.interceptor.UserInterceptor;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer{
	@Autowired
	private UserInterceptor userInterceptor;
	
	//开启匹配后缀型配置
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(true);
	}
	
	//配置jsp前端控制器
	@Bean
	public ViewResolver jspViewResolver() {
		// TODO Auto-generated method stub
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(HandleResourceViewExists.class); //设置检查器
		resolver.setPrefix("/alipay/");
		resolver.setSuffix(".jsp");
		resolver.setViewNames("*");
		resolver.setOrder(0);
		return resolver;
	}
	
	//配置html前端控制器
	@Bean
	public ViewResolver htmlViewResolver() {
		// TODO Auto-generated method stub
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(HandleResourceViewExists.class); //设置检查器
		resolver.setPrefix("/WETA-INF/pages/");
		resolver.setSuffix(".html");
		resolver.setViewNames("*");
		resolver.setOrder(0);
		return resolver;
	}
	
	//添加自定义拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor).addPathPatterns("/fee/**","/pwd/**");
	}
}


package com.ssafy.sharing.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
	
    @Override
	public void addCorsMappings(CorsRegistry registry) {
    	System.out.println("ㅂ듓ㅂㄷ샙ㄷㅂ대숍");
        registry.addMapping("/**") //모든 요청에 대해서
                .allowedOrigins("*"); //허용할 오리진들
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("쀼뜌루쀼쀼");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/home/ubuntu/images/"); //리눅스 root에서 시작하는 폴더 경로
    }	
}

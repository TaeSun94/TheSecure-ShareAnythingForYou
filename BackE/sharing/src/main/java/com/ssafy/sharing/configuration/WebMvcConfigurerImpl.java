package com.ssafy.sharing.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
	@Value("${image.absolute.location}")
	private String imageLocation;
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 요청에 대해서
                .allowedOrigins("*"); //허용할 오리진들
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println(imageLocation);
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(imageLocation); //리눅스 root에서 시작하는 폴더 경로
    }
}

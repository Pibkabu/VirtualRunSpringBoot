package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
//		registry.addResourceHandler("/image_race/**").addResourceLocations("file://" + System.getProperty("user.dir") + "/src/main/image_race/");
//		registry.addResourceHandler("/image_user/**").addResourceLocations("file://" + System.getProperty("user.dir") + "/src/main/image_user/");
		
		registry.addResourceHandler("/image_race/**").addResourceLocations("file:image_race/");
		registry.addResourceHandler("/image_user/**").addResourceLocations("file:image_user/");
	}
}

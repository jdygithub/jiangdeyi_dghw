package com.zking.zkingedu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 *  webmvc基本配置，主要添加对上传文件直接显示的功能
 * <br/>需要资料欢迎大家加<b>班主任Amy老师QQ：2454227267 </b>
 * <br/>交流技术欢迎大家加<b>Jason老师QQ：1626883728</b>
 * 
 * @author Jason @章鱼教育 www.zyejy.com
 * @date 2019-04-11
 *
 */
@Configuration
@PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true)
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Value("${fileStoreLocation}")
	private String fileStoreLocation;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		if (!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
		//让上传的文件可以直接被访问
		registry.addResourceHandler("/upload/**").addResourceLocations( "file:/" + fileStoreLocation);

	}
}

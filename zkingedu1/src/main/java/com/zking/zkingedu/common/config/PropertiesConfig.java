package com.zking.zkingedu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 提供对自定义配置的访问能力 <br/>
 * 需要资料欢迎大家加<b>班主任Amy老师QQ：2454227267 </b> <br/>
 * 交流技术欢迎大家加<b>Jason老师QQ：1626883728</b>
 * 
 * @author Jason @章鱼教育 www.zyejy.com
 * @date 2019-04-11
 *
 */
@Configuration
@PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true)
public class PropertiesConfig {
	/**
	 * 文件上传的存储文件夹的跟目录
	 */

	@Value("${fileStoreLocation}")
	private String fileStoreLocation;

	public String getFileStoreLocation() {
		return fileStoreLocation;
	}

	public void setFileStoreLocation(String fileStoreLocation) {
		this.fileStoreLocation = fileStoreLocation;
	}

}

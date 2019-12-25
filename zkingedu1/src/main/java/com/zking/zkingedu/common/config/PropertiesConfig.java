package com.zking.zkingedu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 文件上传
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

package com.zking.zkingedu.common.utils;

import com.zking.zkingedu.common.config.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具类，主要提供文件夹创建和文件名处理及文件保存功能 <br/>
 * 需要资料欢迎大家加<b>班主任Amy老师QQ：2454227267 </b> <br/>
 * 交流技术欢迎大家加<b>Jason老师QQ：1626883728</b>
 * 
 * @author Jason @章鱼教育 www.zyejy.com
 * @date 2019-04-11
 *
 */
@Component
public class FileUploadUtil {

	Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

	@Resource
	private PropertiesConfig config;

	/**
	 * 保存文件
	 * 
	 * @param file
	 * @return
	 */
	public String saveFile(MultipartFile file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date());
		String orignalName = file.getOriginalFilename();
		int index = orignalName.lastIndexOf('.');
		String extName = "";
		if (index != -1) {
			extName = orignalName.substring(index);
		}
		String fileName = UUID.randomUUID() + extName;
//		String dirName = config.getFileStoreLocation() + dateStr + "/";//添加时间目录
		String dirName = config.getFileStoreLocation();
		// 创建文件夹
		if (!new File(dirName).exists()) {
			new File(dirName).mkdirs();
		}
		File fullFileName = new File(dirName + fileName);
		while (fullFileName.exists()) {
			fileName = UUID.randomUUID() + extName;
			fullFileName = new File(dirName + fileName);
		}
		// 保存文件
		try (FileOutputStream fos = new FileOutputStream(fullFileName)) {
			FileCopyUtils.copy(file.getInputStream(), fos);
//			return dateStr + "/" + fileName;
			return fileName;
		} catch (FileNotFoundException e) {
			logger.error("文件不存在：", fullFileName);
		} catch (IOException e) {
			logger.error("文件上传错误：", e);
		}
		return null;
	}
}

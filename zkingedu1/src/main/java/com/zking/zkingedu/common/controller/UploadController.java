package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现文件上传处理的控制器 <br/>
 * 需要资料欢迎大家加<b>班主任Amy老师QQ：2454227267 </b> <br/>
 * 交流技术欢迎大家加<b>Jason老师QQ：1626883728</b>
 * 
 * @author Jason @章鱼教育 www.zyejy.com
 * @date 2019-04-11
 *
 */
@Controller
@RequestMapping("/img")
public class UploadController {

	@Resource
	private FileUploadUtil uploadUtil;

	
	/**
	 * 文件处理，请求路径应该为/img/upload
	 * @param file 
	 * @return
	 */
	@RequestMapping("upload")
	@ResponseBody
	public List<String> doUpload(MultipartFile[] file) {
		List<String> files = new ArrayList<>();
		if (file == null) {
			return files;
		}
		for (MultipartFile f : file) {
			String fileName = uploadUtil.saveFile(f);
			if (fileName != null) {
				files.add(fileName);
			}
		}
		return files;
	}

}

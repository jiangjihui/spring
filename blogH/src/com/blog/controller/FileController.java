package com.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.blog.tools.FileUtils;


/** 
* 类说明：文件上传处理类
* @author jjh
* @version time：2017年8月22日 下午8:24:17 
* 
*/
@Controller
public class FileController
{
    private static Logger logger = Logger.getLogger(FileController.class);

	/**
	 * 处理富文本编辑器上传图片到服务器
	 * @param request
	 * @return
	 */
	@RequestMapping("/upnotefile")
	@ResponseBody /* SpringMVC 返回json */
	public Map<String, Object> upnotefile(HttpServletRequest request)
	{
		HashMap<String, Object> modelMap = new HashMap<>(1);
		try
		{
			String path = FileUtils.saveFile(request).replace(",","");
			if (path != null && !"".equals(path))
			{
				logger.info("上传图片路径："+path);
				modelMap.put("success", path);
			}
		} catch (Exception e)
		{
			logger.info("添加图片失败！");
			modelMap.put("error", "添加图片失败！");
		}
		return modelMap;
	}
}

package com.blog.tools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 类说明：文件工具类
 * 
 * @author jjh
 * @version time：2017年8月25日 下午1:59:49
 * 
 */
public class FileUtils
{
	private static Logger logger = Logger.getLogger(FileUtils.class);

	public static String saveFile(HttpServletRequest request) throws IOException
	{
		// 转换为文件类型的request
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> photos = multipartRequest.getFiles("file-zh[]");
		String imgpath = "";
		for (MultipartFile photo : photos)
		{
			System.out.println("文件："+photo+"\n 文件名："+photo.getOriginalFilename());
			if (photo.getSize() != 0L)
			{
				String path = request.getSession().getServletContext().getRealPath(VariableX.UPFILE_DIR);		//获取文件保存路径
				String fileName = DateX.getNowMill() + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));		//获取文件名
				File targetFile = new File(path, fileName);
				if (!targetFile.getParentFile().exists())
				{
					targetFile.getParentFile().mkdirs(); // 如果goodPhoto目录不存在，则新建一个
				}
				if (!targetFile.exists())
				{
					targetFile.mkdirs();
				}
				photo.transferTo(targetFile);
				//imgpath += "'"+ fileName+"',";
				imgpath += VariableX.UPFILE_DIR + "/" + fileName+",";
			}
		}
		return imgpath;
	}
}

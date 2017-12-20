package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月26日 上午8:36:00 
* 
*/
@Controller
public class SystemController
{

	/**
	 * 主页
	 * @return
	 */
	//@RequestMapping({"/main","/main/","/index"})
	public String index()
	{
		return "main";
	}
	
	@RequestMapping("go/{model}")
	public String go(@PathVariable String model)
	{
		return model;
	}

}

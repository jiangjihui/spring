package com.blog.controller;

import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.bean.Content;
import com.blog.bean.Customer;
import com.blog.service.IContentSerivce;
import com.blog.tools.DateX;
import com.blog.tools.StringX;
import com.blog.tools.VariableX;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月26日 上午9:18:39 
* 
*/
@Controller
public class ContentController
{
	@Autowired
	IContentSerivce contentService;
	
	/**
	 * 保存blog
	 * @param note
	 * @param session
	 * @return
	 */
	@RequestMapping("savenote")
	public String saveNote(String title,String note,HttpSession session,Model model)
	{
		if (!StringX.isEmpty(note))
		{
			Customer customer = (Customer)(session.getAttribute("customer"));
			if (customer == null)
			{
				model.addAttribute("error", "请登录后再发表");
				return "addnote";
			}
			Content content = new Content();
			content.setNote(new String(Base64.getDecoder().decode(note)));
			content.setCustomerid(customer.getId());
			content.setCustomername(customer.getLoginname());
			content.setTitle(title);
			contentService.save(content);
		}
		return "redirect:main";
	}

	@RequestMapping("main")
	public String showNote(Integer page,Model model)
	{
		page = (page == null) ? 1 : page;
		PageHelper.startPage(page, VariableX.BLOG_PAGESIZE);
		List<Content> list = contentService.list(null,VariableX.YES);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		return "main";
	}
	
	@RequestMapping("list")
	public String showNote(Integer customerid,Integer page,Model model)
	{
		page = (page == null) ? 1 : page;
		PageHelper.startPage(page, VariableX.BLOG_PAGESIZE);
		List<Content> list = contentService.list(customerid,VariableX.YES);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		return "main";
	}
	
	@RequestMapping("customerlist")
	public String customerList(Integer customerid,Integer page,Model model)
	{
		page = (page == null) ? 1 : page;
		PageHelper.startPage(page, VariableX.BLOG_PAGESIZE);
		List<Content> list = contentService.list(customerid,VariableX.YES);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("customerid", customerid);
		return "customerlist";
	}
	
	/**
	 * 删除blog
	 * @param id blog流水号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delnote")
	public HashMap<String, Object> delNote(Integer id)
	{
		HashMap<String, Object> map = new HashMap<>();
		if (contentService.del(id))
		{
			map.put("success", "删除成功");
		}
		else {
			map.put("error", "删除失败，请刷新页面重试");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("checkLogin")
	public HashMap<String, Object> checkLoginAjax(HttpSession session)
	{
		HashMap<String, Object> map = new HashMap<>();
		if (checkCustomerLogin(session))
		{
			map.put("success", "用户已登录");
		}
		else {
			map.put("error", "请登录后操作");
		}
		return map;
	}
	
	/**
	 * 获取搜索条件下的blog列表
	 * @return
	 */
	@RequestMapping("datelist")
	public String getDateList(String date,Integer page,Model model)
	{
		page = (page == null) ? 1 : page;
		PageHelper.startPage(page, VariableX.BLOG_PAGESIZE);
		List<Content> list;
		try
		{
			list = contentService.groupCountByCreateTime(DateX.dateMonthFromString(date));
		} catch (ParseException e)
		{
			e.printStackTrace();
			return "main";
		}
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("date", date);
		return "datelist";
	}
	
	/**
	 * 获取搜索条件下的blog列表
	 * @return
	 */
	@RequestMapping("search")
	public String search(String condition,Integer page,Model model,HttpSession session)
	{
		page = (page == null) ? 1 : page;
		if (StringX.isEmpty(condition))
		{
			condition = (String) session.getAttribute("condition");
		}
		PageHelper.startPage(page, VariableX.BLOG_PAGESIZE);
		List<Content> list = contentService.search(condition);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("condition", condition);
		return "search";
	}
	
	public boolean checkCustomerLogin(HttpSession session)
	{
		Customer customer = (Customer)(session.getAttribute("customer"));
		if (customer == null)
		{
			return false;
		}
		return true;
	}
}

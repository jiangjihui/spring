package com.blog.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.bean.Customer;
import com.blog.bean.Friend;
import com.blog.service.ICustomerService;
import com.blog.tools.StringX;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午8:44:13 
* 
*/
@Controller
public class CustomerController
{
	@Autowired
	ICustomerService customerService;
	
	/**
	 * 登录
	 * @param loginname
	 * @param passwd
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("login")
	public String login(String loginname,String passwd,Model model,HttpSession session)
	{
		if (StringX.isEmpty(loginname)||StringX.isEmpty(passwd))
		{
			return "login";
		}
		try
		{
			Customer customer = new Customer();
			customer.setLoginname(loginname);
			customer.setPasswd(passwd);
			customer = customerService.login(customer);
			session.setAttribute("customer", customer);
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return "login";
		}
		return "redirect:main";
	}
	
	/**
	 * 注册
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping("signup")
	public String register(Customer customer,Model model)
	{
		if (customer == null || customer.getLoginname() == null)
		{
			return "signup";
		}
		try
		{
			customer = customerService.register(customer);
			model.addAttribute("loginname", customer.getLoginname());
			model.addAttribute("passwd", customer.getPasswd());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return "signup";
		}
		return "redirect:login";
	}
	
	/**
	 * 忘记密码
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping("forgot")
	public String forgot(Customer customer,Model model)
	{
		if (customer == null || customer.getLoginname() == null)
		{
			return "forgot";
		}
		try
		{
			customer = customerService.forgot(customer);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return "forgot";
		}
		return "redirect:login";
	}

	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("customer");
		return "login";
	}
	
	/**
	 * 用户信息
	 * @return
	 */
	@RequestMapping("customerinfo")
	public String customerInfo(Integer id,HttpSession session,Model model)
	{
		Customer customer = (Customer)(session.getAttribute("customer"));
		if (customer != null)
		{
			Customer customerInfo = customerService.customerInfo(id);
			Friend friend = customerService.FriendInfo(customer, id);
			model.addAttribute("cust", customerInfo);
			model.addAttribute("friend", friend);
		}
		return "customerinfo";
	}

	/**
	 * 添加关注
	 * @param id
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("specialcare")
	public HashMap<String, Object> specialCare(Integer id,HttpSession session)
	{
		HashMap<String, Object> map = new HashMap<>();
		if (id == null)
		{
			map.put("error", "关注失败，请刷新页面后重试");
			return map;
		}
		Customer customer = (Customer)(session.getAttribute("customer"));
		if (customer != null)
		{
			customerService.specialCare(customer, id);
			map.put("success", "已关注");
		}
		else {
			map.put("error", "请登录后操作");
		}
		return map;
	}
	
	/**
	 * 取消关注
	 * @param id
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelspecialcare")
	public HashMap<String, Object> cancelSpecialCare(Integer id,HttpSession session)
	{
		HashMap<String, Object> map = new HashMap<>();
		if (id == null)
		{
			map.put("error", "取消关注失败，请刷新页面后重试");
			return map;
		}
		Customer customer = (Customer)(session.getAttribute("customer"));
		if (customer != null)
		{
			customerService.cancelSpecialCare(customer, id);
			map.put("success", "已取消关注");
		}
		else {
			map.put("error", "请登录后操作");
		}
		return map;
	}
	
}

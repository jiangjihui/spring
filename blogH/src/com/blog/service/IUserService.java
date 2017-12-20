package com.blog.service;

import com.blog.bean.User;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午8:33:59 
* 
*/
public interface IUserService
{
	//管理员登录
	public User login(User user);
}

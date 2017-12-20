package com.blog.service;
/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午8:27:48 
* 
*/

import com.blog.bean.Customer;
import com.blog.bean.Friend;

public interface ICustomerService
{
	//用户注册
	public Customer register(Customer customer) throws Exception;
	
	
	/**
	 * 用户登录
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public Customer login(Customer customer) throws Exception;
	
	/**
	 * 忘记密码(重置密码)
	 * @param customer 用户信息
	 * @return
	 * @throws Exception
	 */
	public Customer forgot(Customer customer) throws Exception;
	
	/**
	 * 添加关注
	 * @param customer 关注者
	 * @param friendid	被关注者
	 * @return
	 * @throws Exception
	 */
	public boolean specialCare(Customer customer,Integer friendid);
	
	/**
	 * 根据id获取customer
	 * @param id
	 * @return
	 */
	public Customer customerInfo(Integer id);
	
	/**
	 * 获取对应的好友信息
	 * @param id
	 * @return
	 */
	public Friend FriendInfo(Customer customer,Integer friendid);


	/**
	 * 取消关注
	 * @param customer
	 * @param id
	 * @return
	 */
	public boolean cancelSpecialCare(Customer customer, Integer id);
	
	
	/**
	 * 取消关注
	 * @param customer
	 * @param id
	 * @return
	 */
	public boolean cancelSpecialCare(Integer id);
	
}

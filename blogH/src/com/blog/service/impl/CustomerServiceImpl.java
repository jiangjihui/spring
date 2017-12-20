package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.bean.Customer;
import com.blog.bean.CustomerExample;
import com.blog.bean.CustomerExample.Criteria;
import com.blog.bean.Friend;
import com.blog.bean.FriendExample;
import com.blog.dao.CustomerMapper;
import com.blog.dao.FriendMapper;
import com.blog.service.ICustomerService;
import com.blog.tools.DateX;
import com.blog.tools.VariableX;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月26日 上午11:11:08 
* 
*/
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService
{
	@Resource
	CustomerMapper customerDao;
	@Resource
	FriendMapper friendDao;

	@Override
	public Customer register(Customer customer) throws Exception
	{
		if (checkCustomerExist(customer) != null)
		{
			throw new Exception("账号已存在，请更换账号名称");
		}
		customer.setCreatetime(DateX.nowDate().toString());
		customer.setStatus(VariableX.YES);
		customer.setScore(VariableX.SCORE_INITIAL);
		customerDao.insert(customer);
		return customer;
	}

	@Override
	public Customer login(Customer customer) throws Exception
	{
		if (customer == null)  throw new Exception("请输入用户信息");
		
		Customer c = checkCustomerExist(customer);
		if (c == null)
		{
			throw new Exception("用户"+customer.getLoginname()+" 不存在，请检查");
		}
		if (!c.getPasswd().equals(customer.getPasswd()))
		{
			throw new Exception("密码错误，请重新输入密码");
		}
		customer = c;
		if (!VariableX.YES.equals(customer.getStatus()))
		{
			throw new Exception("用户"+customer.getLoginname()+"已被禁止登录");
		}
		if (!DateX.compareDate(customer.getLasttime(), new Date().toString()))
		{
			customer.setScore(customer.getScore()+VariableX.SCORE_LOGIN);
		}
		customer.setOnline(VariableX.YES);
		customer.setLasttime(DateX.nowDate().toString());
		customerDao.updateByPrimaryKey(customer);
		return customer;
	}
	
	/**
	 * 检查用户是否存在
	 * @param customer
	 * @return
	 */
	public Customer checkCustomerExist(Customer customer)
	{
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoginnameEqualTo(customer.getLoginname());
		List<Customer> list = customerDao.selectByExample(example);
		if (list.size() != 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public Customer forgot(Customer customer) throws Exception
	{
		Customer c = checkCustomerExist(customer);
		if(!c.getEmail().equals(customer.getEmail()))
		{
			throw new Exception("用户邮箱错误，请检查");
		}
		c.setPasswd(customer.getPasswd());
		customerDao.insert(c);
		return c;
	}

	@Override
	public boolean specialCare(Customer customer, Integer friendid) 
	{
		Friend friend = new Friend();
		friend.setCreatetime(DateX.nowDate().toString());
		friend.setFriendid(friendid);
		friend.setOwnerid(customer.getId());
		friend.setStatus(VariableX.YES);
		friend.setSpecial(VariableX.NO);
		return friendDao.insert(friend)>0 ? true : false;
	}

	@Override
	public Customer customerInfo(Integer id)
	{
		return customerDao.selectByPrimaryKey(id);
	}

	@Override
	public Friend FriendInfo(Customer customer, Integer friendid)
	{
		if (friendid == null)
		{
			return null;
		}
		FriendExample example = new FriendExample();
		com.blog.bean.FriendExample.Criteria criteria = example.createCriteria();
		criteria.andOwneridEqualTo(customer.getId());
		criteria.andFriendidEqualTo(friendid);
		List<Friend> list = friendDao.selectByExample(example);
		if (list.size()==0)
		{
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean cancelSpecialCare(Customer customer, Integer id)
	{
		return friendDao.deleteByPrimaryKey(FriendInfo(customer, id).getId()) > 0 ? true : false;
	}

	@Override
	public boolean cancelSpecialCare(Integer id)
	{
		return friendDao.deleteByPrimaryKey(id)>0 ? true : false;
	}

}

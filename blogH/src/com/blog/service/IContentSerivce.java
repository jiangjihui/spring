package com.blog.service;

import java.util.Date;
import java.util.List;

import com.blog.bean.Content;
import com.blog.bean.Customer;
import com.github.pagehelper.PageInfo;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午8:35:42 
* 
*/
public interface IContentSerivce
{
	//保存博客
	public boolean save(Content content);
	//删除
	public boolean del(Integer id);
	//查找博客
	public Content find(Customer customer);
	//获取博客
	public List<Content> list(Integer customerid,String status);
	
	public List<Content> groupCountByCreateTime(Date date);
	//搜索博客
	public List<Content> search(String condition);
}

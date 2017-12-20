package com.blog.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.bean.Content;
import com.blog.bean.ContentExample;
import com.blog.bean.ContentExample.Criteria;
import com.blog.bean.Customer;
import com.blog.dao.ContentMapper;
import com.blog.service.IContentSerivce;
import com.blog.tools.DateX;
import com.blog.tools.StringX;
import com.blog.tools.VariableX;
import com.github.pagehelper.PageInfo;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月26日 上午9:24:41 
* 
*/
@Service("contentSerivce")
public class ContentServiceImpl implements IContentSerivce
{
	@Resource
	ContentMapper contentDao;

	@Override
	public boolean save(Content content)
	{
		content.setStatus(VariableX.YES);
		content.setCreatetime(DateX.nowDate().toString());
		contentDao.insert(content);
		return true;
	}

	@Override
	public boolean del(Integer id)
	{
		return contentDao.deleteByPrimaryKey(id)>0 ? true:false;
	}

	@Override
	public Content find(Customer customer)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Content> list(Integer customerid,String status)
	{
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		if (!StringX.isEmpty(customerid))
		{
			criteria.andCustomeridEqualTo(customerid);
		}
		if (!StringX.isEmpty(status))
		{
			criteria.andStatusEqualTo(status);
		}
		example.setOrderByClause("createtime desc");
		return contentDao.selectByExample(example);
	}

	/**
	 * 获取指定日期一个月之类的blog
	 * @param date 
	 * @return
	 */
	@Override
	public List<Content> groupCountByCreateTime(Date date)
	{
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatetimeLessThanOrEqualTo(DateX.getDateDiff(date, Calendar.MONTH, 1).toString());
		criteria.andCreatetimeGreaterThan(date.toString());
		example.setOrderByClause("createtime desc");
		return contentDao.selectByExample(example);
	}

	@Override
	public List<Content> search(String condition)
	{
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		if (!StringX.isEmpty(condition))
		{
			criteria.andTitleLike("%"+condition+"%");					//根据标题搜索
		}
		example.setOrderByClause("createtime desc");
		return contentDao.selectByExample(example);
	}

}

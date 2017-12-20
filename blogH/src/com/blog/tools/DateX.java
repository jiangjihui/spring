package com.blog.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午9:01:23 
* 
*/
public class DateX
{
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_FULL_DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";
	
	/**
	 * 获取当前系统时间戳
	 * @return
	 */
	public static long getNowMill()
	{
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
//	public static Date nowDate()
//	{
//		return new Date();
//	}

	/**
	 * 获取当前日期
	 * @return
	 */
	public static String nowDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
		String date = sdf.format(new Date());
		return date;
	}
	
	/**
	 * 得到当前日期的字符串形式
	 * @return 当前日期
	 */
	public static String getNowDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
		String date = sdf.format(new Date());
		return date;
	}
	
	
	/**
	 * 得到所传入日期的字符串形式
	 * @return 当前日期
	 */
	public static String dateString(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 得到所传入日期的字符串形式
	 * @return 当前日期
	 */
	public static String dateString(String date)
	{
		return date;
//		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FULL_DATE_FORMAT);
//		return sdf.format(date);
	}
	
	/**
	 * 比较两个日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1,Date date2)
	{
		if (date1 == null || date2 == null)
		{
			return false;
		}
		return dateString(date1).equals(dateString(date2));
	}
	
	/**
	 * 比较两个日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(String date1,String date2)
	{
		if (date1 == null || date2 == null)
		{
			return false;
		}
		return dateString(date1).equals(dateString(date2));
	}
	
	/**
	 * 获取当前日期的第day天
	 * @return String 形式
	 */
	public static String weekDayString(int day)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, day);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(calendar.getTime());
	}
	
	/**
	 * 获取日期差值
	 * @param date 日期
	 * @param unit	差值单位
	 * @param value 差值数 
	 * @return
	 */
	public static String getDateDiffString(Date date,int unit,int value)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(unit, value);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(calendar.getTime());
	}
	
	/**
	 * 获取日期差值
	 * @param date 日期
	 * @param unit	差值单位 Calendar常量
	 * @param value 差值数 
	 * @return
	 */
	public static Date getDateDiff(Date date,int unit,int value)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(unit, value);
		return calendar.getTime();
	}
	
	/**
	 * 字符串转化为日期格式
	 * @param dString
	 * @return 日期格式
	 * @throws ParseException 
	 */
	public static Date dateFromString(String dString) throws ParseException
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.parse(dString);
	}
	
	
	/**
	 * 获取所传入日期的月份
	 * @param dString
	 * @return 日期格式
	 * @throws ParseException 
	 */
	public static Date dateMonthFromString(String dString) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.parse(dString);
	}
	
}

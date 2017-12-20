package com.blog.tools;
/** 
* 类说明：
* @author jjh
* @version time：2017年9月25日 下午9:33:04 
* 
*/
public class StringX
{
	/**
	 * 判断字符是否为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string)
	{
		if (string == null || string.length() == 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * 判断字符是否为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(Integer string)
	{
		if (string == null)
		{
			return true;
		}
		return false;
	}

}

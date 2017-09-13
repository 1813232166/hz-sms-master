package com.hzdjr.hzwd.modules.borrow.util;
import java.util.Calendar;

import com.hzdjr.hzwd.common.utils.JedisUtils;
public class BorrowAliasUtils {

	/**
	 *标的中文别名
	 *@param
	 *@return 标的中文编号
	 * */
	public static String getBorrowNo() throws Exception{
		if(!JedisUtils.existsObject("borrowNo")){
			JedisUtils.set("borrowNo", "0", 86400000);
		}
		Calendar cal = Calendar.getInstance();  
		StringBuffer borrowName = new StringBuffer();
		String bNo = JedisUtils.incr("borrowNo") + "";
	    String bNoStr = String.format("%05d",Integer.valueOf(bNo)); 
	    int year = cal.get(Calendar.YEAR);//年
	    String yearStr = year+"";
	    String yearSub = (String) yearStr.subSequence(yearStr.length()-2, yearStr.length());
	    int month = cal.get(Calendar.MONTH) + 1;
	    String monthStr = String.format("%02d",month); 
	    int day = cal.get(Calendar.DATE);
	    String dayStr = String.format("%02d",day); 
	    borrowName.append("普享标").append(yearSub).append(monthStr).append(dayStr).append(bNoStr);
	    System.out.println(borrowName);
	    return borrowName+"";
	}
	
	/**
	 *@param
	 *@return 散标中文编号
	 */
	public static String getBorrowNoWithoutName(){
		return getNo("borrowNo");
	}
	
	/**
	 *@param
	 *@return 散标集中文编号
	 */
	public static String getBorrowListNo(){
		return getNo("borrowListNo");
	}
	
	public static String getNo(String borrowSign){
		if(!JedisUtils.existsObject(borrowSign)){
			JedisUtils.set(borrowSign, "0", 86400000);
		}
		Calendar cal = Calendar.getInstance();  
		StringBuffer borrowName = new StringBuffer();
		String bNo = JedisUtils.incr(borrowSign) + "";
		String bNoStr = String.format("%05d",Integer.valueOf(bNo)); 
		int year = cal.get(Calendar.YEAR);//年
		String yearStr = year+"";
		String yearSub = (String) yearStr.subSequence(yearStr.length()-2, yearStr.length());
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = String.format("%02d",month); 
		int day = cal.get(Calendar.DATE);
		String dayStr = String.format("%02d",day); 
		borrowName.append(yearSub).append(monthStr).append(dayStr).append(bNoStr);
		System.out.println(borrowName);
		return borrowName.toString();
	}
}

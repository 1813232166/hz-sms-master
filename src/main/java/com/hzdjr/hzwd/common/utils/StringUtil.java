package com.hzdjr.hzwd.common.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
  * @ClassName: StringUtil 
  * @Description:  字符串处理工具类
  * @author chenmingqiang
  * @date 2017年4月19日 下午1:49:42 
  *
 */
public class StringUtil {
	
	
	/**
	* @Title: abbr 
	* @Description: 缩略字符串,将length后字符串用...代替（不区分中英文字符）
	* @param str 目标字符串
	* @param length 截取长度   
	* @return String    
	* @throws 
	* @author chenmingqiang
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int currentLength = 0;
		for (char c : str.toCharArray()) {
			currentLength += String.valueOf(c).length();
			if (currentLength <= length - 3) {
				sb.append(c);
			} else {
				sb.append("...");
				break;
			}
		}
		return sb.toString();
	}


	
	
	/**
	* @Title: abbrZh 
	* @Description: 缩略字符串,将length后字符串用...代替（区分中英文字符,汉字在页面占两个长度位置）
	* @param str 目标字符串
	* @param length 截取长度  
	* @return String    
	* @throws 
	* @author chenmingqiang
	 */
	public static String abbrZh(String str, int length) {
		if (str == null) {
			return "";
		}
		//汉字正则
		Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
		Matcher matc = null;
		StringBuilder sb = new StringBuilder();
		int currentLength = 0;
		for (char c : str.toCharArray()) {
			matc = pattern.matcher(String.valueOf(c));
			if (matc.find()) {
				currentLength += String.valueOf(c).length() + 1;
			} else {
				currentLength += String.valueOf(c).length();
			}
			if (currentLength <= length - 3) {
				sb.append(c);
			} else {
				sb.append("...");
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 手机匿名显示.
	 * Assert.assertEquals("123**6789", Strings.anonMobile("123456789"));
	 * Assert.assertEquals("135****8888", Strings.anonMobile("13566668888"));
	 * Assert.assertEquals("aB字****测试cD", Strings.anonMobile("aB字符串截取测试cD"));
	 * 
	 * @param mobile 手机号
	 * @return
	 * @author zhaomingwei
	 */
	public static String anonMobile(String mobile) {
		return anonymous(mobile, 3, 4, 0);
	}

	/**
	 * 银行卡匿名显示
	 * Assert.assertEquals("*****************8266", Strings.anonMobile("6216665000001788266"));
	 * @param cardNum
	 * @return
	 * @author zhaomingwei
	 */
	public static String annoBankCard(String cardNum){
		return anonymous(cardNum, 0, 4, 0);
	}
	/**
	 * 选取头尾各一个字符，中间用3个*号代替
	 */
	public static String anonNameThree(String name) {
		return anonymous(name, 1, 1, 3);
	}

	/**
	 * 字符串匿名显示.
	 * Assert.assertEquals("1*******9", Strings.anonName("123456789"));
	 * Assert.assertEquals("1*********8", Strings.anonName("13566668888"));
	 * Assert.assertEquals("a*********D", Strings.anonName("aB字符串截取测试cD"));
	 * 
	 * @param in 源字符串
	 * @return
	 * @author zhaomingwei
	 */
	public static String anonName(String name) {
		return anonymous(name, 1, 1, 0);
	}

	/**
	 * 字符匿名显示.
	 * Assert.assertEquals("1*******9", Strings.anonymous("123456789", 1, 1));
	 * Assert.assertEquals("135****8888", Strings.anonymous("13566668888", 3, 4));
	 * Assert.assertEquals("aB字****测试cD", Strings.anonymous("aB字符串截取测试cD", 3, 4));
	 * 
	 * @param in 输入字符
	 * @param leftLen 左边显示明文字符长度
	 * @param rightLen 右边显示明文字符长度
	 * @param starLen 指定*的长度
	 * @return
	 * @author zhaomingwei
	 */
	public static String anonymous(String in, int leftLen, int rightLen, int starLen) {
		if (StringUtils.isBlank(in)) {
			return "";
		}
		if (leftLen < 0) {
			leftLen = 0;
		}
		if (rightLen < 0) {
			leftLen = 0;
		}
		in = StringUtils.trim(in);
		StringBuilder sb = new StringBuilder();
		int loop = 0;
		for (int i = 0; i < in.length(); i++) {
			if (i < leftLen || i >= in.length() - rightLen) {
				sb.append(in.charAt(i));
			} else {
				if (starLen > 0) {
					if (loop <= starLen) {
						sb.append("*");
					}
				} else {
					sb.append("*");
				}
			}
			loop++;
		}
		return sb.toString();
	}


	
	
	/**
	* @Title: getCarInfo 
	* @Description: 根据身份证号获取出生日期和性别
	* @param CardCode    
	* @return Map<String,Object>    
	* @throws 
	* @author chenmingqiang
	 */
	public static Map<String, Object> getCarInfo(String CardCode)  throws Exception {  
        Map<String, Object> map = new HashMap<String, Object>();  
        String year = CardCode.substring(6).substring(0, 4);// 得到年份  
        String yue = CardCode.substring(10).substring(0, 2);// 得到月份  
        String day=CardCode.substring(12).substring(0,2);//得到日  
        String sex;  
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别  
            sex = "0";//女 
        } else {  
            sex = "1";//男  
        }  
        map.put("sex", sex);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(year+"-"+yue+"-"+day);
        map.put("birthday", date);
        return map;  
        }

	
	
	/**
	* @Title: is 
	* @Description: 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	* @param obj    
	* @return boolean    
	* @throws 
	* @author chenmingqiang
	 */
    @SuppressWarnings("rawtypes")
	public static boolean is(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Integer) {
            return (((Integer) obj) == 0);
        }
        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!is(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}

package com.hzwealth.sms.modules.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hzwealth.sms.common.utils.PropertiesLoader;

/**
 * 获取请求流水号
 * @author Administrator
 *
 */
public class ReqUrlPreUUID {
	
    private static PropertiesLoader loader = new PropertiesLoader("sms.properties");
	
	private static String reqUrl ;
	
	static{
		reqUrl = loader.getProperty("reqUrlPreUUID");
	}
	
	public  String  GetRequestNo(String code){
		String preStr = getHtmlConentByUrl(reqUrl+code);
		preStr = preStr.substring(1,preStr.length()-1);
		String endStr = getStringRandom(11);
		return  preStr+endStr;
	}
	
	public static String getHtmlConentByUrl(String ssourl) {
		try {
			URL url = new URL(ssourl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setInstanceFollowRedirects(false);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.connect();
			StringBuffer sb = new StringBuffer();
			String line = "";
			BufferedReader URLinput = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
			while ((line = URLinput.readLine()) != null) {
				sb.append(line);
			}
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	//生成随机数字和字母,  
    public  String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
	
	public static void main(String[] args) {
//		ReqUrlPreUUID reqUrl = new ReqUrlPreUUID();
//		System.out.println(reqUrl.GetRequestNo("1020"));
	}

}

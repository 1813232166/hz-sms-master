package com.hzdjr.hzwd.modules.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpUtils {

	public static String sendPostMessage(String url,Map<String,Object> params) throws Exception {
		String res = "";
		HttpClient httpclient=new HttpClient();  
        //post请求  
        PostMethod postmethod=new PostMethod(url); 
        postmethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        NameValuePair[] postData=new NameValuePair[params.size()]; 
        int i = 0; 
        for(String key:params.keySet()){
        	postData[i] = new NameValuePair(key,(String)params.get(key)); 
        	i++;
        } 
        postmethod.addParameters(postData);  
        httpclient.executeMethod(postmethod);
        res = postmethod.getResponseBodyAsString();
        postmethod.releaseConnection();  
        return  res;
	}
	
	public static String urlPostMethod(String url,String params) {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        try {
            if(params != null && !params.trim().equals("")) {
                RequestEntity requestEntity = new StringRequestEntity(params,"text/xml","UTF-8");
                method.setRequestEntity(requestEntity);
            }
            httpClient.executeMethod(method);
            String responses= method.getResponseBodyAsString();
            method.releaseConnection();
            return responses;
        } catch (Exception e) {
            System.out.println("短信发送异常："+params);
            e.printStackTrace();
        }
        return "";
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
	
	public static void main(String[] args) {
		try {
			System.out.println(getHtmlConentByUrl("https://www.baidu.com"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

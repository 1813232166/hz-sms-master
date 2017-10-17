package com.hzwealth.sms.modules.borrow.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hzwealth.sms.common.utils.PropertiesLoader;


/**
 * 补标工具
 * @author wzb
 *
 */
public class HttpClientPostUtil {
	private static final PropertiesLoader prop = new PropertiesLoader("hzwd.properties");
	
    public String getDataToUrl(String data,String methodType) throws IOException{
    	String url = prop.getProperty("lend.http.server.url");;  
		String ret="";
		URL urlObject = new URL(url);  
		HttpURLConnection httpConn = (HttpURLConnection) urlObject.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setRequestMethod(methodType);
		DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
		out.write(data.getBytes("UTF-8"));
		out.flush();
		out.close();			
		InputStream stream = httpConn.getInputStream();
		DataInputStream in = new DataInputStream(stream);
		byte[] bin = null;
		byte[] inc = new byte[1024];
		int datelength = 0;
		int insize = 0;
		while ((insize = in.read(inc)) != -1){
			int oldlength = datelength;
			datelength += insize;
			byte[] oldbin = new byte[datelength];
			for (int i = 0; i < oldlength; i++)
				oldbin[i] = bin[i];
			for (int i = oldlength; i < datelength; i++)
				oldbin[i] = inc[i - oldlength];
			bin = oldbin;
		}
		ret = new String(bin,"UTF-8");
		in.close();
		stream.close();
		return ret;
	}
}

    

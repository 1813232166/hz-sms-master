package com.hzwealth.sms.modules.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataHttpClient {
	
	public static String postData(String url,String data,String methodType) throws Exception{
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

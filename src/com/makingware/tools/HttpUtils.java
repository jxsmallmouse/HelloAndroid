package com.makingware.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * @title Http请求工具类
 * @description 请求http数据,单例模式
 * @company 探索者网络工作室(www.tsz.net)
 * @author michael Young (www.YangFuhai.com)
 * @version 1.0
 * @created 2012-8-19
 */
public class HttpUtils implements HttpApi{
	private static final String DEBUG_TAG = "HttpUtils";
	private HttpUtils() {} //单例模式中，封闭创建实例接口
	
	private static HttpUtils httpUtils = null; 
	
	//提供了一个可以访问到它自己的全局访问点
	public static HttpUtils get(){
		if(httpUtils == null)
			httpUtils = new HttpUtils();
		return httpUtils;
	}
	
	/**
	 * 获取某个url的内容
	 * @param strUrl
	 * @return
	 */
	//@Override
	public String getUrlContext(String strUrl){
		 InputStream is = null;
		    int len = 500;
		        
		    try {
		        URL url = new URL(strUrl);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setReadTimeout(10000 /* milliseconds */);
		        conn.setConnectTimeout(15000 /* milliseconds */);
		        conn.setRequestMethod("GET");
		        conn.setDoInput(true);
		        conn.connect();
		        int response = conn.getResponseCode();
		        Log.d(DEBUG_TAG, "The response is: " + response);
		        is = conn.getInputStream();
		        
		        //这里指获取了500（len=500）字节，如果想
		        //整个网页全部获取可以用conn.getContentLength()来代替len
		        String contentAsString = readInputStream(is, len);
		        return contentAsString;
		       
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
		        if (is != null) {
		            try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        } 
		    }
		    return null;
	}
	
	/**
	 * 读取 InputStream 内容
	 * @param stream
	 * @param len
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private String readInputStream(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}

}


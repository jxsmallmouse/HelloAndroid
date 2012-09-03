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
 * @title Http���󹤾���
 * @description ����http����,����ģʽ
 * @company ̽�������繤����(www.tsz.net)
 * @author michael Young (www.YangFuhai.com)
 * @version 1.0
 * @created 2012-8-19
 */
public class HttpUtils implements HttpApi{
	private static final String DEBUG_TAG = "HttpUtils";
	private HttpUtils() {} //����ģʽ�У���մ���ʵ���ӿ�
	
	private static HttpUtils httpUtils = null; 
	
	//�ṩ��һ�����Է��ʵ����Լ���ȫ�ַ��ʵ�
	public static HttpUtils get(){
		if(httpUtils == null)
			httpUtils = new HttpUtils();
		return httpUtils;
	}
	
	/**
	 * ��ȡĳ��url������
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
		        
		        //����ָ��ȡ��500��len=500���ֽڣ������
		        //������ҳȫ����ȡ������conn.getContentLength()������len
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
	 * ��ȡ InputStream ����
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


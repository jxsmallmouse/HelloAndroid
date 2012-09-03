package com.makingware.tools;

/**
 * @title Httpģ�鹤��
 * @description ��ȡhttpģ��
 * @company ̽�������繤����(www.tsz.net)
 * @author michael Young (www.YangFuhai.com)
 * @version 1.0
 * @created 2012-8-21
 */
public class HttpFactory {
	
	private static final int HTTP_CONFIG= 1 ;//http���÷�ʽ��0��httpConnect��1��httpclient
	
	public static HttpApi getHttp(){
		if(HTTP_CONFIG == 0)
			return HttpUtils.get();
		else if(HTTP_CONFIG == 1)
			return HttpClientUtils.get();
		
		return null;
	}

}

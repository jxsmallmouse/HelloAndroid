package com.makingware.tools;

/**
 * @title Http模块工厂
 * @description 获取http模块
 * @company 探索者网络工作室(www.tsz.net)
 * @author michael Young (www.YangFuhai.com)
 * @version 1.0
 * @created 2012-8-21
 */
public class HttpFactory {
	
	private static final int HTTP_CONFIG= 1 ;//http调用方式，0是httpConnect，1是httpclient
	
	public static HttpApi getHttp(){
		if(HTTP_CONFIG == 0)
			return HttpUtils.get();
		else if(HTTP_CONFIG == 1)
			return HttpClientUtils.get();
		
		return null;
	}

}

package com.makingware.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * @title HttpClient���������Ϣ
 * @description ����
 * @company ̽�������繤����(www.tsz.net)
 * @author michael Young (www.YangFuhai.com)
 * @version 1.0
 * @created 2012-8-21
 */
public class HttpClientUtils implements HttpApi {

	private static final String DEBUG_TAG = "HttpClientUtils";
	private static final String CHARSET_UTF8 = "UTF-8";

	private HttpClientUtils() {} // ����ģʽ�У���մ���ʵ���ӿ�

	private static HttpClientUtils httpClientUtils = null;

	/**
	 * �ṩ��һ�����Է��ʵ����Լ���ȫ�ַ��ʵ�
	 * @return
	 */
	public static HttpClientUtils get() {
		if (httpClientUtils == null)
			httpClientUtils = new HttpClientUtils();
		return httpClientUtils;
	}
	
	
	//@Override
	public String getUrlContext(String strUrl) {
		String responseStr = null;// �������󣬵õ���Ӧ
		DefaultHttpClient httpClient = null;
		HttpGet httpGet = null;
		try {
			strUrl = urlEncode(strUrl.trim(), CHARSET_UTF8);
			httpClient = getDefaultHttpClient(null);
			httpGet = new HttpGet(strUrl);
			responseStr = httpClient.execute(httpGet, strResponseHandler);
		} catch (ClientProtocolException e) {
			Log.e(DEBUG_TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(DEBUG_TAG, e.getMessage());
		} catch (Exception e) {
			Log.e(DEBUG_TAG, e.getMessage());
		} finally {
			abortConnection(httpGet, httpClient);
		}
		return responseStr;
	}

	
	
	/**
	 * ת��http����ַ��ֻ�����Ľ���ת��
	 * 
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String urlEncode(String str, String charset)
			throws UnsupportedEncodingException {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(str);
		StringBuffer b = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
		}
		m.appendTail(b);
		return b.toString();
	}

	
	
	/**
	 * �����������ƺ��쳣�Զ��ָ�����
	 */
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// �Զ���Ļָ�����
		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			// ���ûָ����ԣ���Http�������쳣ʱ���Զ�����3��
			if (executionCount >= 3) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};

	
	// ʹ��ResponseHandler�ӿڴ�����Ӧ��HttpClientʹ��ResponseHandler���Զ��������ӵ��ͷţ�
	//��������ӵ��ͷŹ���
	private static ResponseHandler<String> strResponseHandler = new ResponseHandler<String>() {
		// �Զ�����Ӧ����
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? CHARSET_UTF8
						: EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};
	
	
	

	/**
	 * ��ȡDefaultHttpClientʵ��
	 * 
	 * @param charset
	 * @return
	 */
	private static DefaultHttpClient getDefaultHttpClient(final String charset) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
		// HttpVersion.HTTP_1_1);
		// ģ�������(��Щ������ֻ֧����������ʣ��������ģ����~~~)
		// httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		// httpclient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,Boolean.FALSE);
		
		// ����ʱ
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		// ��ȡ��ʱ
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,5000);
		httpclient.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET,
				charset == null ? CHARSET_UTF8 : charset);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}

	/**
	 * �ͷ�HttpClient����
	 * 
	 * @param hrb
	 * @param httpclient
	 */
	private static void abortConnection(final HttpRequestBase httpRequestBase,
			final HttpClient httpclient) {
		if (httpRequestBase != null) {
			httpRequestBase.abort();
		}
		if (httpclient != null) {
			httpclient.getConnectionManager().shutdown();
		}
	}

}

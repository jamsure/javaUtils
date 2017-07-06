package com.xz.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author david
 *
 */
public class HttpClientUtil {
 /**
  * post请求
  * @param url
  * @param param
  * @return
* @throws IOException 
  */
 public static String jsonDataPost(String url,Map<String,String> params){
	 String result = null;
	 CloseableHttpClient httpclient = HttpClientUtil.createDefault();
	 HttpPost httpPost = new HttpPost(url);
	  //拼接参数
	 List<NameValuePair> list = new ArrayList<NameValuePair>();
	 for (Map.Entry<String, String> entry : params.entrySet()) {
	         String key = entry.getKey().toString();
	         String value = entry.getValue().toString();
	         NameValuePair pair = new BasicNameValuePair(key, value); 
	         list.add(pair); 
	 }
 		CloseableHttpResponse response=null;
 		try {
		httpPost.setEntity(new UrlEncodedFormEntity(list));
		response = httpclient.execute(httpPost);
		/**请求发送成功，并得到响应**/  
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
      	  HttpEntity httpEntity = response.getEntity();
      	  result = EntityUtils.toString(httpEntity);
       }
		 } catch (Exception e) {
		e.printStackTrace();
		 }
 		return result;
 }
 
 public static String jsonDataGet(String url){
	 	String result = null;
	    try {
	        //发送get请求
	        HttpGet request = new HttpGet(url);
	        HttpResponse response = HttpClientBuilder.create().build().execute(request);

	        /**请求发送成功，并得到响应**/
	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            /**读取服务器返回过来的json字符串数据**/
	        	result = EntityUtils.toString(response.getEntity());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
 
 public static HttpEntity isDataGet(String url){
	 HttpEntity httpEntity = null;
	    try {
	        //发送get请求
	        HttpGet request = new HttpGet(url);
	        HttpResponse response = HttpClientBuilder.create().build().execute(request);

	        /**请求发送成功，并得到响应**/
	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            /**读取服务器返回过来的数据**/
	        	httpEntity = response.getEntity();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return httpEntity;
	}
 
 /**
  * post请求
  * @param url
  * @param param
  * @return
* @throws IOException 
  */
 public static HttpEntity isDataPost(String url,Map<String,String> params){
	 HttpEntity httpEntity = null;
	 CloseableHttpClient httpclient = HttpClientUtil.createDefault();
	 HttpPost httpPost = new HttpPost(url);
	  //拼接参数
	 List<NameValuePair> list = new ArrayList<NameValuePair>();
	 for (Map.Entry<String, String> entry : params.entrySet()) {
	         String key = entry.getKey().toString();
	         String value = entry.getValue().toString();
	         NameValuePair pair = new BasicNameValuePair(key, value); 
	         list.add(pair); 
	 }
 		CloseableHttpResponse response=null;
 		try {
		httpPost.setEntity(new UrlEncodedFormEntity(list));
		response = httpclient.execute(httpPost);
		/**请求发送成功，并得到响应**/  
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
      	  httpEntity = response.getEntity();
       }
		 } catch (Exception e) {
		e.printStackTrace();
		 }
 		return httpEntity;
 }
 
 /**
   * Creates {@link CloseableHttpClient} instance with default
   * configuration.
   */
   public static CloseableHttpClient createDefault() {
       return HttpClientBuilder.create().build();
   }

} 

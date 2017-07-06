package com.fh.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendSms {
	
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	private static String appkey = "23901316";
	
	private static String  secret= "d97a3b46f7cdea2aef05621b0abab1bb";
	
	
	public static AlibabaAliqinFcSmsNumSendResponse sendSms(String content, String templateId, String mobile) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("钻石婚恋");
		req.setSmsParamString(content);
		req.setRecNum(mobile);
		req.setSmsTemplateCode(templateId);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		return rsp;
	}
	
	public static void main(String[] args) throws ApiException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "123456");
		map.put("product", "钻石婚恋");
		SendSms.sendSms(JSON.toJSONString(map), "SMS_70120539", "18755123314");
	}
}

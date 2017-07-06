package com.fh.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLUtil {
	
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = string2Inputstream(strxml);
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			m.put(k, v);
		}
		in.close();
		return m;
	}
	
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}
	
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = string2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}
	
	public static InputStream string2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	public static void main(String[] args) throws JDOMException, IOException {
		String xml = "<xml>"+
				   "<return_code><![CDATA[SUCCESS]]></return_code>"+
				   "<return_msg><![CDATA[OK]]></return_msg>"+
				   "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>"+
				   "<mch_id><![CDATA[10000100]]></mch_id>"+
				   "<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>"+
				   "<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>"+
				   "<result_code><![CDATA[SUCCESS]]></result_code>"+
				   "<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>"+
				   "<trade_type><![CDATA[APP]]></trade_type>"+
				   "</xml>";
		
		Map<String, String> map = XMLUtil.doXMLParse(xml);
		
		 for (String v : map.values()) {
			   System.out.println("value= " + v);
		}
	}
	

}
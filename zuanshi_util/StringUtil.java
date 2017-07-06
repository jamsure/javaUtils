package com.fh.util;

import java.security.MessageDigest;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	/**获取字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   } 
	
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty(&quot;&quot;)        = true
	 * StringUtil.isEmpty(&quot; &quot;)       = false
	 * StringUtil.isEmpty(&quot;bob&quot;)     = false
	 * StringUtil.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}
	
	/**
	 * @Title: getMd5String
	 * @Description: 获取MD5加密字符串
	 * @param text
	 * @return String
	 */
	public static String getMd5String(String text) {
		String result = "";
		if (isNullOrEmpty(text)) {
			return result;
		}

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] tmp = (text + "ccollider.com").getBytes();
			messageDigest.update(tmp);
			byte[] bytes = messageDigest.digest(); // 以"ISO-8859-1"方式解析searchkey
			StringBuffer buffer = new StringBuffer(32);
			for (byte item : bytes) {
				int b = item & 0xFF;
				if (b < 0x10)
					buffer.append('0');
				buffer.append(Integer.toHexString(b));
			}
			result = buffer.toString(); // 再用"utf-8"格式表示name
		} catch (Exception e) {
			result = "";
		}
		return result;
	}
	/**
	 * 判断字符串是否为null或着空串（包括空格）
	 *
	 * @param source
	 *
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String source) {
		if (source == null) {
			return true;
		}
		source = source.trim();
		if ("".equals(source)) {
			return true;
		}

		return false;
	}
}

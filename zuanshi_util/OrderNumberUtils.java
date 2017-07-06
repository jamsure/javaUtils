package com.fh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberUtils {
	/*
	 * 订单号(日期+四位随机数)
	 */
	public static String createNoncestr() {
		String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		int x;//定义两变量
		Random ne=new Random();
		x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		
		return str+x;
	}
	/*
	 * 激活码
	 */
	public static String codeNoncestr() {
		String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		String vcode = getRandomCode();
		return str+vcode;
	}
	/**  
  	 * @Description:生成六位随机数
  	 * @Date: 2016年7月29日10:52:35
  	 */
  	public static String getRandomCode() {
		Random rd = new Random();
		return new StringBuilder().append(rd.nextInt(10))
				.append(rd.nextInt(10)).append(rd.nextInt(10))
				.append(rd.nextInt(10)).append(rd.nextInt(10))
				.append(rd.nextInt(10)).toString();
	}
}

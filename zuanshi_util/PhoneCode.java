package com.fh.util;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description:验证码相关
 * @ClassName: PhoneCode 
 * @packege: com.xz.ym.core.utils
 * @author: xiaolong
 * @date: 
 * 
 * 短信发送的类型
 * 1.注册  2.找回密码
 */
public class PhoneCode {
	public static String getTEMID(String msgType){
		String TEMID="error";
		switch (msgType) {
		case "1":
			TEMID=StateUtil.sendMsgTempType.TEMPREGIST;
			break;
		case "2":
			TEMID=StateUtil.sendMsgTempType.TEMPFORGET;
			break;
		default:
			break;
		}
		return TEMID;
	}
	
	/**
	 * @Description:验证手机号是否正确
	 * @Title: chkPhone 
	 * @author: fankai
	 * @date: 2017年4月21日上午10:30:45
	 * @param phone
	 * @return
	 * @return: Boolean
	 */
	public static Boolean chkPhone(String userName,HttpServletRequest request) {
		String phone=(String)request.getSession().getAttribute("phone");
		if(phone==null||"".equals(phone)){
			return false;
		}else if (userName.matches("1[34578]\\d{9}")&&phone.equals(userName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description:验证手机验证码是否正确
	 * @Title: chkPhoneCode 
	 * @author: fankai
	 * @date: 2017年4月21日上午10:37:30
	 * @param phoneCode
	 * @param request
	 * @return
	 * @return: Boolean
	 */
	public static Boolean chkPhoneCode(String phoneCode,HttpServletRequest request) {
		String code=(String)request.getSession().getAttribute("phoneCode");
		if (code==null||"".equals(code)) {
			return false;
		}
		if (code.equals(phoneCode)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Description：验证手机号码和验证码是否正确	
	 * @Title: chkPhoneAndCode 
	 * @author: fankai
	 * @date: 2017年4月24日上午10:26:30
	 * @param phone
	 * @param phoneCode
	 * @param request
	 * @return
	 * @return: Boolean
	 */
	public static Boolean chkPhoneAndCode(String phone,String phoneCode,HttpServletRequest request){
		String code=(String)request.getSession().getAttribute(phone);
		if (code==null||"".equals(code)) {
			return false;
		}
		if (code.equals(phoneCode)) {
			return true;
		}
		return false;
	}
}

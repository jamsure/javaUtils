package com.xz.base.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

/**
 * @author Shangcp
 *
 * 支付工具类
 * 
 */
public class PaymentUtil {
	/**支付种类**/
	public static String ALIPAY = "alipay";//支付宝支付
	public static String UNIONPAY = "union";//银联支付
	public static String JFDF = "JFDF";//积分支付
	public static String BALANCEPAY = "balancepay";//余额支付
	
	public static String WXPAY = "wxzf";//微信支付,扫码默认支付方式，后期需修改
	public static String WXNATIVEPAY = "native";//微信扫码支付
	public static String WXAPPPAY = "app";//app微信支付
	
	/** 银联支付需要参数   **/
	public static String version = "5.0.0";
	public static String encoding = "UTF-8";
	/**支付宝需要参数**/
	public static String service = "create_direct_pay_by_user";//即时到账
	public static String payment_type = "1";//支付类型,(商品支付)
	


    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
	// 商户发送交易时间 格式:YYYYMMDDhhmmss
	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	
	/**
	 * 创建跳转html
	 * @param payType 支付方式
	 * @param action  请求地址
	 * @param hiddens 请求参数
	 * @return 跳转html
	 */
	public static String createHtml(String payType,String action, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		if(PaymentUtil.UNIONPAY.equalsIgnoreCase(payType)){//银联支付
			sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
			sf.append("<form id = \"pay_form\"  action=\"" + action
					+ "\" method=\"post\">");
		}
		if(PaymentUtil.ALIPAY.equalsIgnoreCase(payType)){//支付宝支付
			sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
			sf.append("<form id=\"pay_form\"  name=\"pay_form\" action=\"" + action
                    + "_input_charset=" + encoding + "\" method=\"get\">");
		}
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
	
	/*
	 * 获取微信随机字符串
	 */
	public static String createNoncestr() {
		UUID uuid = UUID.randomUUID();
		String str = Md5Util.getMD5Code(uuid.toString());
		return str.toUpperCase();
	}
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean strIsNotnull(String str){
		if(str!=null&&!"".equals(str)){
			return true;
		}
		return false;
	}
}

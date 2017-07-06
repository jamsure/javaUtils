package com.fh.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 配置相关的变量
 * @author 1332117017
 *
 */
public class StateUtil {

	/**
	 * 充值类型
	 * @author 1332117017
	 * 描述：（0线下，1线上）
	 */
	public static final class RECHARGETYPE {
		/**
		 * 0：线下
		 * */
		public static final String LINE_DOWN = "0";
		/**
		 * 1：线上
		 * */
		public static final String LINE_UP = "1";
	}

	/**
	 * 充值状态
	 * @author 1332117017
	 * 描述：（0,失败，1成功）
	 */
	public static final class RECHARGESTATE {
		/**
		 * 0：失败
		 * */
		public static final String RECHARGEFAIL = "0";
		/**
		 * 1：成功
		 * */
		public static final String RECHARGESUCCESS = "1";
	}

	/**
	 * 支付通道（1.通联支付，2手动，3银联在线）',
	 * @author 1332117017
	 * 
	 */
	public static final class PAYTYPE {
		/**
		 * 1：通联支付
		 * */
		public static final String ALLINPAY = "1";
		/**
		 * 1：手动
		 * */
		public static final String MANUAL = "2";
		/**
		 * 1：银联在线
		 * */
		public static final String UNIONPAYONLINE = "3";
	}
	
	/**
	 * BigDecimal类型转化
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = new BigDecimal(0) ;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
	
	/**
	 * 各项服务的时间，1天，3天，7天，1月，3月，6月，1年
	 * @author 1332117017
	 *
	 */
	public static final class  serviceTime{
		/**
		 * 一天
		 */
		public static final int oneDay = 1;
		/**
		 * 三天
		 */
		public static final int threeDay = 3;
		/**
		 * 七天
		 */
		public static final int sevenDay = 7;
		/**
		 * 一月
		 */
		public static final int oneMonth = 1;
		/**
		 * 三月
		 */
		public static final int threeMonth = 3;
		/**
		 * 十二月
		 */
		public static final int twelveMonth = 12;
		/**
		 * 6月
		 */
		public static final int sixMonth = 6;
		/**
		 * 1年
		 */
		public static final int oneYear = 1;
		
		/**
		 * 终生
		 */
		public static final int LIFELONG_MEMBERSHIP_FEE = 1200;
	}

	/**
	 * 爱情定制
	 * typeService 定制类型（1私人定制，2爱情定制，3家族联姻）
	 * @author 1332117017
	 *
	 */
	public static final class  typeService{
		/**
		 * 私人定制--1
		 */
		public static final String PERSONALTAILOR = "1";
		/**
		 * 爱情定制--2
		 */
		public static final String LOVECUSTOMIZATION = "2";
		/**
		 * 家族联姻--3
		 */
		public static final String FAMILYMARRIAGE = "3";
	}
	
	/**
	 * 爱情定制
	 * '定制类型
	 * （当typeService为2时，其类型有3种{1标准办，2高级版，3尊享版}）\r\n            
	 * 家族联姻定制类型（当typeService为3时，其类型有2种{1标准办，2高级版}）',
	 * @author 1332117017
	 *
	 */
	public static final class  privateService{
		/**
		 * 爱情定制-标准版-1
		 */
		public static final String LOVESTANDARD = "1";
		/**
		 * 爱情定制-高级版-2
		 */
		public static final String LOVESENIOR = "2";
		/**
		 * 爱情定制-尊享版-3
		 */
		public static final String LOVEENJOY = "3";
		/**
		 * 家族联姻-标准版-1
		 */
		public static final String FAMILYSTANDARD = "1";
		/**
		 * 家族联姻-高级-2
		 */
		public static final String FAMILYSENIOR = "2";
		
	}
	
	
	
	
	
	/**
	 * 用户信息--图片类型
	 * @author xiaolong
	 * 描述：图片类型（1：身份证，2：学历信息，3：房产信息，4：车辆信息，5：形象视频，6：个人照片）
	 */
	public static final class picType {
		/**
		 * 1：身份证
		 * */
		public static final String  ID_CARD = "1";
		/**
		 *2：学历信息
		 * */
		public static final String EDUCATIONAL = "2";
		/**
		 *3：房产信息
		 * */
		public static final String HOUSING = "3";
		/**
		 *4：车辆信息
		 * */
		public static final String VEHICLE = "4";
		/**
		 *5：形象视频
		 * */
		public static final String VIDEO = "5";
		/**
		 *6：个人照片
		 * */
		public static final String IMAGE = "6";
	}
	
	/**
	 * 认证信息：0未认证，1待认证，2认证通过，3认证未通过
	 * @author xiaolong
	 * 
	 */
	public static final class identInfo{
		/**
		 * 0未认证
		 * */
		public static final String NO_IDENT = "0";
		/**
		 * 1待认证
		 * */
		public static final String DAI_IDENT = "1";
		/**
		 * 2认证通过
		 * */
		public static final String IDENT_YES = "2";
		/**
		 * 3认证未通过
		 * */
		public static final String IDENT_NO = "3";
	}
	/**
	 * 认证信息    --- 0不通过，1通过 2待认证
	 * @author xiaolong
	 * 
	 */
	public static final class identState{
		/**
		 *0不通过
		 * */
		public static final String NO_STATE = "0";
		/**
		 * 1通过
		 * */
		public static final String YSE_STATE = "1";
		/**
		 * 2待认证
		 * */
		public static final String NO_IDENT = "2";
	}
	
	/**
	 * 状态    --- 状态（0否，1是）
	 * @author fankai
	 * 
	 */
	public static final class yesOrNo{
		/**
		 *0否
		 * */
		public static final String NO = "0";
		/**
		 * 1是
		 * */
		public static final String YES = "1";
	}
	/**
	 * 状态（0过期，1正常）
	 * @author fankai
	 * 
	 */
	public static final class loginState{
		/**
		 *0过期
		 * */
		public static final String OVERDUE = "0";
		/**
		 * 1正常
		 * */
		public static final String NORMAL = "1";
	}
	/**
	 * 类型（1：关于我们，2平台规范，3：帮助中心）
	 * @author fankai
	 * 
	 */
	public static final class manageType{
		/**
		 *1关于我们
		 * */
		public static final String ABOUT_US = "1";
		/**
		 *2平台规范
		 * */
		public static final String PLATFORM_SPECIFICATION = "2";
		/**
		 *3：帮助中心
		 * */
		public static final String HELP_CENTER = "3";
		/**
		 *4：用户协议
		 * */
		public static final String AGREEMENT = "4";
		
	}
	
	/**
	 * 充值项目（0--会员服务，1--高级婚恋助理，2私人助理）
	 * @author fankai
	 * 
	 */
	public static final class RechargeProgram{
		/**
		 *0会员服务
		 * */
		public static final String MEMBER_SERVICE = "0";
		/**
		 * 1高级婚恋助理
		 * */
		public static final String SUPER_ASSISTANT = "1";
		/**
		 * 2私人助理
		 * */
		public static final String PERSONAL_ASSISTANT = "2";
		/**
		 * 3置顶
		 * */
		public static final String STICK = "3";
		/**
		 * 4爱情定制
		 * */
		public static final String LOVE_CUSTOMIZATION = "4";
	}
	
	/**
	 * 初始化默认状态
	 * @Description: 
	 * @ClassName: DefaultState 
	 * @packege: com.fh.util
	 * @author: fankai
	 * @date: 2017年6月5日 下午5:19:57
	 */
	public static final class DefaultState {
		/**
		 *0未咨询
		 * */
		public static final String NO_CONSULT = "3d827383dd6e4e7d87541637db49e54f";
		
		/**
		 * 帮我约她 未处理
		 */
		public static final String NO_HANDEL = "3943b108839848428ca767a0803c1c50";
		
		/**
		 *1管理员id
		 * */
		public static final String ADMIN = "1";
		
		/**
		 * 私人助理
		 */
		public static final String PA_ID = "5cddc13f5ce6463f8e9b97867e598428";
		
	}

	/**************
	 * 获取状态
	 **************/
	
	/**
	 * 获取开通月数
	 * @Description: 
	 * @packege: com.fh.controller.app.appuser
	 * @author: fankai
	 * @date: 2017年6月1日 下午4:40:05 
	 * @param type
	 * @return
	 */
	public static int getMonths(int type) {
		int i=0;
		switch (type) {//充值时间（0--一个月，1--三个月，2--六个月，3--一年,4--终生）
		case 0:
			i = StateUtil.serviceTime.oneMonth;
			break;
		case 1:
			i = StateUtil.serviceTime.threeMonth;
			break;
		case 2:
			i = StateUtil.serviceTime.sixMonth;
			break;
		case 3:
			i = StateUtil.serviceTime.twelveMonth;
			break;
		case 4:
			i = StateUtil.serviceTime.LIFELONG_MEMBERSHIP_FEE;
			break;
		}
		return i;	
	}
	
	/**
	 * 获取开通天数
	 * @Description: 
	 * @packege: com.fh.controller.app.appuser
	 * @author: fankai
	 * @date: 2017年6月1日 下午4:40:05 
	 * @param type
	 * @return
	 */
	public static int getDays(int type) {
		int i=0;
		switch (type) {//（0：一天，1：三天，2：七天）
		case 0:
			i = StateUtil.serviceTime.oneDay;
			break;
		case 1:
			i = StateUtil.serviceTime.threeDay;
			break;
		case 2:
			i = StateUtil.serviceTime.sevenDay;
			break;
		}
		return i;	
	}
	
	
	/**
	 * 0：个人标签；1：喜欢的电影；2：喜欢的音乐；3：喜欢的书籍；4：喜欢的美食；5：喜欢的运动
	 * @author 1332117017
	 */
	public static final class  labelTypeCode{
		/**
		 * 0：个人标签
		 */
		public static final String LABEL_PERSONAL = "label_personal";
		/**
		 * 1：喜欢的电影
		 */
		public static final String LABEL_FAVORITE_MOVIES = "label_favorite_movies";
		/**
		 * 2：喜欢的音乐；
		 */
		public static final String LABEL_FAVORITE_MUSIC = "label_favorite_music";
		/**
		 * 3：喜欢的书籍
		 */
		public static final String LABEL_FAVORITE_BOOK = "label_favorite_book";
		/**
		 * 4：喜欢的美食
		 */
		public static final String LABEL_FAVORITE_FOOD = "label_favorite_food";
		/**
		 * 5：喜欢的运动
		 */
		public static final String LABEL_FAVORITE_SPORTS = "label_favorite_sports";
		
	}
	/**
	 * 短信发送的类型
	 * 1.注册   2.找回密码
	 */
	public static final class sendMsgTempType{
		/**
		 * SMS_60355494-->注册
		 * 验证码${code}，您正在注册成为${product}用户，感谢您的支持！
		 */
		public static final String TEMPREGIST="SMS_70120539";
		/**
		 * SMS_61580021-->找回密码
		 * 验证码${code}，您正在找回登录密码，请勿泄露！
		 */
		public static final String TEMPFORGET="SMS_70120537";
		
	}
	/**
	 * 交易类型
	 */
	public static final class tradeType{
		//交易类型
		public static final String TRADETYPE_ORDER = "order";//订单
		public static final String TRADETYPE_RECHARGE = "recharge";//余额充值
	}
	/**
	 * 订单支付状态
	 * 描述：支付状态（0：待支付；1：已支付）
	 * @author Administrator
	 *
	 */
	public static final class orderPayStatus{
		/**
		 * 0：待支付
		 * */
		public static final String ON_PAY = "0";
		/**
		 * 1：已支付
		 * */
		public static final String YES_PAY= "1";
	}
	/**
	 * 支付渠道（0：支付宝；1：微信支付;2余额支付）
	 * @author Administrator
	 * */
	public static final class payWay{
		/**
		 * 0：支付宝
		 */
		public static final String ALIPAY = "0";
		
		/**
		 *  1：微信支付
		 */
		public static final String WXPAY = "1";
		/**
		 *  2：余额支付
		 */
		public static final String BALANCEPAY = "2";
		/**
		 *  3：pos機支付
		 */
		public static final String PAY_POS = "3";
		
	}
	/**
	 * 订单类型（0注册支付  1申请置顶  2爱情定制   3家族联姻 4开通会员   4私人助理）
	 * @author Administrator
	 * */
	public static final class orderType{
		/**
		 * 0：注册支付
		 */
		public static final String REGISTER_PAY = "0";
		
		/**
		 *  1申请置顶
		 */
		public static final String TOP_PAY = "1";
		/**
		 *  2爱情定制
		 */
		public static final String LOVE_PAY = "2";
		/**
		 *   3家族联姻
		 */
		public static final String HOME_PAY = "3";
		/**
		 *  4开通会员
		 */
		public static final String MEMBER_PAY = "4";
		/**
		 *  5私人助理
		 */
		public static final String ZHULI_PAY = "5";
		
	}
	/**
	 * 对应缴费服务支付类型（注册类型（0即可认证  1付费免认证）；申请置顶类型（2申请置顶））；爱情定制（3标准版 4高级版 5尊享版）；家族联姻（6标准版 7尊享版）；开通会员（8开通会员）；私人助理（9私人助理）
	 * @author Administrator
	 * */
	public static final class orderPayType{
		/**
		 * 0即可认证
		 */
		public static final String REN_ZHENG = "0";
		
		/**
		 *  1付费免认证
		 */
		public static final String MIAN_RENZHENG = "1";
		/**
		 *  2申请置顶
		 */
		public static final String SHENQING_TOP = "2";
		/**
		 *   爱情定制（3标准版 ）
		 */
		public static final String LOVAE_PAY_BIAO = "3";
		/**
		 *  爱情定制（ 4高级版）
		 */
		public static final String LOVAE_PAY_GAO = "4";
		/**
		 *  爱情定制（5尊享版）
		 */
		public static final String LOVAE_PAY_HAO = "5";
		/**
		 *  家族联姻（6标准版 ）
		 */
		public static final String JIAZU_BIAO = "6";
		/**
		 *  家族联姻（ 7尊享版）
		 */
		public static final String JIAZU_HAO = "7";
		/**
		 *  8开通会员
		 */
		public static final String OPEN_MENBER = "8";
		/**
		 *  私人助理（9私人助理
		 */
		public static final String SIREN_ZHULI = "9";
		
	}
	
}

/**   
* @Title: Constants.java 
* @Package: com.xz.base.utils 
* @Description: 常量类
* @author: andy
* @date: 2017年2月27日14:37:54
* @version: V1.0   
*/
package com.xz.base.utils;

public class ConstantUtil {
	/**
	 * 租车订单--常量类
	 * @author Administrator
	 * 描述：订单状态（0：待门店审核；1：待平台审核；2：待确认车型；3：待签合同；4：合同待审核；5：待财务审核；6：待取车；7：待还车；8：退还违章押金；9：交易完成；10：审核不通过；11：交易关闭；12：待线上支付）
	 */
	public static final class leaseCarOrderManage {
		/**
		 * 0：待门店审核
		 * */
		public static final String ZC_SHOP_AUDIT = "0";
		/**
		 * 1：待平台审核
		 * */
		public static final String ZC_PLATFORM_AUDIT = "1";
		/**
		 * 2：待确认车型
		 * */
		public static final String ZC_CONFIRM_VEHICLE = "2";
		/**
		 * 3：待签合同
		 * */
		public static final String ZC_PENDING_CONTRACT = "3";
		/**
		 * 4：合同待审核
		 * */
		public static final String ZC_PENDING_CONTRACT_AUDIT = "4";
		/**
		 * 5：待财务审核
		 * */
		public static final String ZC_PENDING_FINANCIAL_AUDIT = "5";
		/**
		 * 6：待取车
		 * */
		public static final String ZC_TAKE_CAR = "6";
		/**
		 * 7：待还车
		 * */
		public static final String ZC_STATE_PENDING_AUDIT_BACK_CHECK = "7";
		/**
		 * 8：退还违章押金
		 * */
		public static final String ZC_DEPOSIT_REFUND = "8";
		/**
		 * 9：交易完成；
		 * */
		public static final String ZC_TRANSACTION_COMPLETED = "9";
		/**
		 * 10：审核不通过；
		 * */
		public static final String ZC_AUDIT_NOT_APPROVRD = "10";
		/**
		 * 11：交易关闭；
		 * */
		public static final String ZC_TRANSACTION_CLOSE = "11";
		/**
		 * 12：待线上支付
		 * */
		public static final String ZC_ONLINE_PAYMENT = "12";
	}
	
	/**
	 * 租车验车状态
	 * @author Administrator
	 * 描述：验车状态（0：待取车验车；1：已验车（待还车验车）；2；已完成）
	 */
	public static final class leaseCarCheckManage {
		/**
		 * 0：待取车验车
		 * */
		public static final String ZC_TAKE_CHECK = "0";
		/**
		 * 1：已验车（待还车验车）
		 * */
		public static final String ZC_STILL_CHECK = "1";
		/**
		 * 2：已完成
		 * */
		public static final String ZC_CHECK_COMPLETED = "2";
	}
	/**
	 *  费用类型
	 * @author Administrator
	 * 描述： 费用类型(0手续费 1不计免赔 2二手车服务费用）
	 */
	public static final class feeManage {
		/**
		 * 0：手续费
		 * */
		public static final String FEE_TYPE_COUNTER = "0";
		/**
		 * 1：不计免赔
		 * */
		public static final String FEE_TYPE_NODEDUCTIBLE = "1";
		/**
		 * 2：二手车服务费用
		 * */
		public static final String FEE_TYPE_SERVICE = "2";
	}
	/**
	 * 租车是否更换车型
	 * @author Administrator
	 * 描述：0：不更换；1：更换；2：不接受更换（关闭订单）
	 */
	public static final class changeModelManage {
		/**
		 * 0：不更换
		 * */
		public static final String ZC_NO_CHANGE = "0";
		/**
		 * 1：更换
		 * */
		public static final String ZC_CHANGE = "1";
		/**
		 * 2：不接受更换（关闭订单）
		 * */
		public static final String ZC_CLOSE_ORDER = "2";
	}
	/**
	 * 验车类型
	 * @author Administrator
	 * 描述：验车类型（0：取车验车单；1：还车验车单）
	 */
	public static final class vehicleType {
		/**
		 * 0：取车验车单
		 * */
		public static final String ZC_QC_TAKE_CHECK = "0";
		/**
		 * 1：还车验车单
		 * */
		public static final String ZC_HC_STILL_CHECK = "1";
	}
	
	/**
	 * 逾期情况状态
	 * @author Administrator
	 * 描述：逾期情况（0：未逾期；1：已逾期）
	 */
	public static final class leaseCarOverdueManage {
		/**
		 * 0：未逾期
		 * */
		public static final String NOT_OVERDUE = "0";
		/**
		 * 1：已逾期
		 * */
		public static final String IS_OVERDUE = "1";
	}
	
	/**
	 * 车辆监控
	 * @author Administrator
	 * 描述：车辆监控（0：正常；1：异常）
	 */
	public static final class leaseCarMonitoringManage {
		/**
		 * 0：正常
		 * */
		public static final String NORMAL = "0";
		/**
		 * 1：异常
		 * */
		public static final String AB_NORMAL = "1";
	}
	
	/**
	 * 租车订单支付方式
	 * 描述：支付方式（0：线上支付；1：线下支付）
	 * @author Administrator
	 *
	 */
	public static final class zcOrderPayMethod {
		/**
		 * 0：线上支付
		 * */
		public static final String ZC_ONLINE_PAY = "0";
		/**
		 * 1：线下支付
		 * */
		public static final String ZC_LINE_PAY= "1";
	}
	/**
	 * 租车订单支付状态
	 * 描述：支付状态（0：待支付；1：已支付）
	 * @author Administrator
	 *
	 */
	public static final class zcOrderPayStatus{
		/**
		 * 0：待支付
		 * */
		public static final String ZC_ON_PAY = "0";
		/**
		 * 1：已支付
		 * */
		public static final String ZC_YES_PAY= "1";
	}
	/**
	 * 二手车添加时
	 * 车辆来源(0---直营或者加盟店1---个人卖家)
	 */
	public static final class oldVechilenSources {
		/**
		 * 0：直营或者加盟店
		 * */
		public static final String NAPASTORES = "0";
		/**
		 * 1：个人卖家
		 * */
		public static final String INDIVIDUALSELLER = "1";
	}
	
	
	/**
	 * 经纪公司-审核状态类型
	 * @author Administrator
	 * */
	public static final class brokerageManage{
		/**
		 * 0：待初审 
		 * */
		public static final String pending_trial = "0";
		
		/**
		 *  1：待复审（初审通过）  
		 * */
		public static final String first_pass = "1";
		
		/**
		 *  2：初审不通过 
		 * */
		public static final String first_not_pass = "2";
		
		/**
		 * 3：复审通过 
		 * */
		public static final String review_pass  = "3";
		
		/**
		 * 4：复审不通过
		 * */
		public static final String review_not_pass = "4";
	}
	
	/**
	 * 链接管理-链接类型
	 * @author Administrator
	 * */
	public static final class linkConfigurationManage{
		/**
		 * 0：热门租车
		 * */
		public static final String LINK_TYPE_LEASE_VEHICLE = "0";
		
		/**
		 *  1：二手车  
		 * */
		public static final String LINK_TYPE_OLD_VEHICLE = "1";
		
		/**
		 *  2：友情链接 
		 * */
		public static final String LINK_TYPE_FRIEND_LINK = "2";
	}
	/**
	 * 门店管理-门店状态
	 * @author Administrator
	 * */
	public static final class storeManage{
		/**
		 * 0：正在营业
		 * */
		public static final String STORE_STATE_YES = "0";
		
		/**
		 *  1：暂停营业  
		 * */
		public static final String STORE_STATE_NO = "1";
		
	}
	/**
	 * 二手车-买车订单状态状态（0：待处理；1：待门店跟进；2：已成交；3：未成交；）
	 * @author Administrator
	 * */
	public static final class oldOrderState{
		/**
		 * 状态（0：待处理）
		 * */
		public static final String ORDER_ATATE_NO = "0";
		
		/**
		 *  状态（1：待门店跟进）
		 * */
		public static final String ORDER_ATATE_UP_GO = "1";
		/**
		 *  状态（2：已成交）
		 * */
		public static final String ORDER_ATATE_DEAL_YES = "2";
		/**
		 *  状态（3：未成交）
		 * */
		public static final String ORDER_ATATE_DEAL_NO = "3";
	}
	/**
	 * 买家来源（0：网上预约；1：电话预约）
	 * @author Administrator
	 * */
	public static final class buySouce{
		/**
		 * 状态（0：网上预约）
		 * */
		public static final String INTNET_APPOINT = "0";
		
		/**
		 *  状态（1：电话预约）
		 * */
		public static final String TEL_APPOINT = "1";
		
	}
	
	/**
	 * '二手车车源状态(0---待审核1---审核不通过2---已上架3---已下架4---已成交)',
	 */
	public static final class oldVechileState{
		/**
		 * 状态（0：待审核）
		 * */
		public static final String OLD_WAIT_AUDIT = "0";
		
		/**
		 *  状态（1：审核不通过）
		 * */
		public static final String OLD_AUDIT_FAIL = "1";
		/**
		 *  状态（2：已上架）
		 * */
		public static final String OLD_VECHILE_GROUDING = "2";
		/**
		 *  状态（3：已下架）
		 * */
		public static final String OLD_VECHILE_UNGROUDING = "3";
		/**
		 *  状态（4：已成交）
		 * */
		public static final String OLD_VECHILE_SUCCESS = "4";
		
	}
	
	/**
	 * 租车车源状态(0---待审核1---审核不通过2---已上架3---已下架4---租赁中)',
	 * @author Administrator
	 *
	 */
	public static final class leaseVehicleState{
		/**
		 * 状态（0：待审核）
		 * */
		public static final String LEASE_WAIT_AUDIT = "0";
		
		/**
		 *  状态（1：审核不通过）
		 * */
		public static final String LEASE_AUDIT_FAIL = "1";
		/**
		 *  状态（2：已上架）
		 * */
		public static final String LEASE_VECHILE_GROUDING = "2";
		/**
		 *  状态（3：已下架）
		 * */
		public static final String LEASE_VECHILE_UNGROUDING = "3";
		/**
		 *  状态（4：租赁中）
		 * */
		public static final String LEASE_VECHILE_IN = "4";
	}
	
	/**
	 * 审核状态	'审核结果(0---审核通过1---审核不通过)',
	 * @author 1332117017
	 *
	 */
	public static final class AuditState {
		/**
		 *  状态（0：审核通过）
		 * */
		public static final String AUDITPASS = "0";
		/**
		 *  状态（1：审核不通过）
		 * */
		public static final String AUDITFAILD = "1";
	}

	/**
	 * 二手车车源上架下架记录表
	 * '操作类型(0---上架1---下架)',
	 * @author 1332117017
	 *
	 */
	public static final class OldVechilePutPullState {
		/**
		 *  状态（0：上架）
		 * */
		public static final String PUTSTATE = "0";
		/**
		 *  状态（1：下架）
		 * */
		public static final String PULLSTATE = "1";
	}
	/**
	 * '车型类型(0---租车1---二手车)',
	 * @author 1332117017
	 *
	 */
	public static final class modelType{
		/**
		 *  0---租车
		 * */
		public static final String MODEL_TYPE_ZC = "0";
		/**
		 *  1---二手车
		 * */
		public static final String MODEL_TYPE_ES = "1";
	}
	
	/**
	 * 二手车-买车订单状态状态（0：待处理；1：待门店跟进；2：车辆已检测；3：车主不卖了；）
	 * @author Administrator
	 * */
	public static final class sellCarOrder{
		/**
		 * 状态（0：待处理）
		 * */
		public static final String ORDER_ATATE_NO = "0";
		
		/**
		 *  状态（1：待门店跟进）
		 * */
		public static final String ORDER_ATATE_UP_GO = "1";
		/**
		 *  状态（2：车辆已检测）
		 * */
		public static final String ORDER_ATATE_DEAL_YES = "2";
		/**
		 *  状态（3：车主不卖了）
		 * */
		public static final String ORDER_ATATE_DEAL_NO = "3";
	}

	/**
	 * '图片类型(0---外观1---内饰2---底盘和发动机3---缺陷)'
	 * @author Administrator
	 * */
	public static final class imageType{
		/**
		 * 状态（0：外观）
		 * */
		public static final String IMAGEEXTERIOR = "0";
		
		/**
		 *  状态（1：内饰）
		 * */
		public static final String IMAGETRIM = "1";
		/**
		 *  状态（2：底盘和发动机）
		 * */
		public static final String IMAGEENGINE = "2";
		/**
		 *  状态（3：缺陷）
		 * */
		public static final String IMAGEBUG = "3";
	}
	/**
	 * '车型管理类型(0---租车1---二手车)'
	 * @author Administrator
	 * */
	public static final class vehicleModelManagementState{
		/**
		 * 状态（0：租车）
		 * */
		public static final String LEASECAR = "0";
		
		/**
		 *  状态（1：二手车）
		 * */
		public static final String OLDVECHILE = "1";
	}

	/**
	 * '用户类型(0---平台用户1---门店用户)',
	 * @author Administrator
	 * */
	public static final class userType{
		/**
		 * 状态（0：平台用户）
		 * */
		public static final String PLATFORMUSER = "0";
		
		/**
		 *  状态（1：门店用户）
		 * */
		public static final String STOREUSER = "1";
	}
	/**
	 * 租车预约类型(0线上预约 1电话预约）
	 * @author Administrator
	 * */
	public static final class appointmentType{
		/**
		 * 状态（0：线上预约）
		 * */
		public static final String APPOINTMENT_UP = "0";
		
		/**
		 *  状态（1：电话预约）
		 * */
		public static final String APPOINTMENT_ONLINE = "1";
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
	 * 品牌推荐表--类型
	 * @author Administrator
	 * */
	public static final class brandManage{
		/**
		 * 推荐品牌类型:0租车品牌
		 * */
		public static final String BRAND_TYPE_ZC = "0";
		
		/**
		 *  推荐品牌类型:1二手车品牌
		 * */
		public static final String BRAND_TYPE_ER = "1";
		
	}
	
	/**
	 * 价格区间表--类型
	 * @author Administrator
	 * */
	public static final class webPrice{
		/**
		 * 价格类型:0租车价格
		 * */
		public static final String PRICE_TYPE_ZC = "0";
		
		/**
		 *  价格类型:1二手车价格
		 * */
		public static final String PRICE_TYPE_ER = "1";
		
	}
	
	/**
	 * 账户是否可用（0可用 1禁用）
	 */
	public static final class isAccountNumberDisable{
		/**
		 * 0---可用
		 */
		public static final String YES = "0";
		/**
		 * 1---禁用
		 */
		public static final String NO = "1";
	}
	
	/**
	 * 注册类型---0--手机注册1--邮箱注册',
	 */
	public static final class registerType{
		/**
		 * 0--手机注册
		 */
		public static final String PHONE_REGIST = "0";
		/**
		 * 1--邮箱注册
		 */
		public static final String EMAIL_REGIST = "1";
	}
	
	/**
	 * 会员等级类型((0---普通会员(新手上路)1---银卡会员(略有所成)2---金卡会员(驾轻就熟)3---铂金会员（钻石会员))
	 * @author 1332117017
	 *
	 */
	public static final class memberGradeType{
		/**
		 * 0---普通会员(新手上路)
		 */
		public static final String GRADE_NEWCOMERS = "0";
		/**
		 * 1---银卡会员(略有所成)
		 */
		public static final String GRADE_SLIGHTLY_INTO = "1";
		/**
		 * 2---金卡会员(驾轻就熟)
		 */
		public static final String GRADE_SKILLED = "2";
		
		/**
		 * 3---铂金会员（钻石会员)
		 */
		public static final String GRADE_DIAMOND = "3";
		
	}
	
	/**
	 * 短信发送的类型
	 * 1.注册、2.登录、3.找回密码、4.预约看车、5.申请加盟、6.提示密码过于简单、7.余额支付
	 */
	public static final class sendMsgTempType{
		/**
		 * SMS_60355494-->注册
		 * 验证码${code}，您正在注册成为${product}用户，感谢您的支持！
		 */
		public static final String TEMPREGIST="SMS_60355494";
		/**
		 * SMS_60355496-->登录
		 *  验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。
		 */
		public static final String TEMPLOGIN="SMS_60355496";
		/**
		 * SMS_61580021-->找回密码
		 * 验证码${code}，您正在找回登录密码，请勿泄露！
		 */
		public static final String TEMPFORGET="SMS_61580021";
		/**
		 * SMS_61530008-->预约看车
		 * 验证码${code}，您正在预约看车，请保持手机畅通！
		 */
		public static final String TEMPAPPOINTMENT="SMS_61530008";
		/**
		 * SMS_61520023-->申请加盟
		 * 验证码${code}，您正在注册成为${product}门店，感谢您的支持！
		 */
		public static final String TEMPJOIN="SMS_61520023";
		
		/**
		 * SMS_69080088-->余额支付
		 * 验证码${code}，您正在通过余额支付，切勿泄露！
		 */
		public static final String TEMPPAY="SMS_69080088";
		
		
	}
	
	
	/**
	 * 是否收藏（0是已收藏 1已取消）
	 */
	public static final class myCollection{
		/**
		 * 0是已收藏
		 */
		public static final String COLLECTION_ADD = "0";
		/**
		 * 1已取消
		 */
		public static final String COLLECTION_CANCEL = "1";
	}
	/**
	 * banner类型(0---跳转到指定网址1---跳转到指定富文本框内容2---纯展示(不可点击))
	 */
	public static final class bannerTypeConet{
		/**
		 *0---跳转到指定网址
		 */
		public static final String BANNER_WAI = "0";
		/**
		 * 1---跳转到指定富文本框内容
		 */
		public static final String BANNER_NEI = "1";
		/**
		 * 2---纯展示
		 */
		public static final String BANNER_TEXT = "2";
	}
	/**
	 * 终端类型(0---web1---app)
	 */
	public static final class bannerType{
		/**
		 *0---web
		 */
		public static final String BANNER_WEB = "0";
		/**
		 * 1---app
		 */
		public static final String BANNER_APP = "1";
	}

	/**
	 * 二手车车源信息是否修改过(0---未修改，1---修改过)',
	 */
	public static final class isModified{
		/**
		 *0---未修改
		 */
		public static final String UNMODIFIED = "0";
		/**
		 * 1---修改过
		 */
		public static final String MODIFIDE = "1";
	}
	/**
	 * 消费类型（0：消费 1：充值）
	 */
	public static final class consumptionType{
		/**
		 * 0消费
		 */
		public static final String CONSUM_TYPE = "0";
		/**
		 * 1充值
		 */
		public static final String Recharge_TYPE = "1";
	}
	/**
	 * '问题类型(0---随心租车1---随心买卖)',
	 */
	public static final class commonProblem{
		/**
		 * (0---随心租车
		 */
		public static final String SX_LEASE = "0";
		/**
		 * 1---随心买卖
		 */
		public static final String SX_SELL = "1";
	}
	
	/**
	 * banner
	 * @author Administrator
	 * 描述：终端类型(0---web，1---app)',
	 */
	public static final class banner {
		/**
		 * 0：web
		 * */
		public static final String web = "0";
		/**
		 * 1：app
		 * */
		public static final String app = "1";
		
	}
}

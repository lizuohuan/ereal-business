package com.magic.ereal.business.util;

public class StatusConstant {

	
	// 错误代码
	/**获取成功*/
	public static final Integer SUCCESS_CODE = 200;
	// 201 备用
	/**获取失败*/
	public static final Integer Fail_CODE = 202;
	/**参数异常*/
	public static final Integer ARGUMENTS_EXCEPTION = 203;

	
	
	/**没有权限 错误代码*/
	public static final Integer NOT_AGREE = 1001;
	/**对象不存在*/
	public static final Integer OBJECT_NOT_EXIST = 1002;
	/**字段不能为空*/
	public static final Integer FIELD_NOT_NULL= 1003;
	/**正在审核*/
	public static final Integer PENDING = 1004;
	/**未登录*/
	public static final Integer NOTLOGIN= 1005;
	/**没有数据*/
	public static final Integer NO_DATA = 1006;
	/**账户被冻结*/
	public static final Integer ACCOUNT_FROZEN = 1007;
	/**订单无效*/
	public static final Integer ORDER_INVALID = 1008;
	/**状态异常*/
	public static final Integer ORDER_STATUS_ABNORMITY = 1009;
	/**对象已经存在*/
	public static final Integer OBJECT_EXIST = 1010;

	/** 用户不存在 */
	public static final Integer USER_DOES_NOT_EXIST = 1050;
	/** 用户名或者密码错误 */
	public static final Integer PASSWORD_ERROR = 1051;

	/** 电话号码已存在 */
	public static final Integer PHONE_NUMBER_THERE = 1052;
	/** 账号已存在 */
	public static final Integer USER_NAME_ALREADY_EXISTS = 1053;
	/** 邮箱已存在 */
	public static final Integer EMAIL_IS_EXISTENCE = 1054;




	// 设备类型
	/**android*/
	public static final Integer ANDROID=0;
	/**ios*/
	public static final Integer IOS = 1;


	/**员工状态 : 实习*/
	public static final Integer USER_STATUE_INTERNSHIP = 0;
	/**员工状态 : 磨合期*/
	public static final Integer USER_STATUE_ADJUST_PERIOD = 1;
	/**员工状态 : 正式*/
	public static final Integer USER_STATUE_REGULAR = 2;
	/**员工状态 : 离职*/
	public static final Integer USER_STATUE_DIMISSION = 3;


	// 工作日志 状态
	/**工作日志状态 : 草稿 临时保存*/
	public static final Integer WORKDIARY_STATUS_DRAFT = 4000;
	/**工作日志状态 : 经理待审核*/
	public static final Integer WORKDIARY_STATUS_PENDING = 4001;
	/**工作日志状态 : 经理审核不通过*/
	public static final Integer WORKDIARY_STATUS_REJECT = 4002;
	/**工作日志状态 : 经理审核通过*/
	public static final Integer WORKDIARY_STATUS_APPROVED = 4003;
	/**工作日志状态 : 综合部经理审核通过*/
	public static final Integer WORKDIARY_SYNTHESIZE_STATUS_APPROVED = 4004;
	/**工作日志状态 : 综合部经理审核不通过*/
	public static final Integer WORKDIARY_SYNTHESIZE_TATUS_REJECT = 4005;

	// 工作日志 状态进度 话术
	public static final String WORKDIARY_DETAIL_SUBMIT = "提交工作日志";
	public static final String WORKDIARY_DETAIL_PENGDING = "待审核";
	public static final String WORKDIARY_DETAIL_REJECT = "审核不通过";
	public static final String WORKDIARY_DETAIL_SECOND_SUBMIT = "待修改提交日志";
	public static final String WORKDIARY_DETAIL_APPROVED = "一阶段审核通过，等待综合部经理审核";
	public static final String WORKDIARY_DETAIL_SYNTHESIZE_APPROVED = "综合部经理审核通过";
	public static final String WORKDIARY_DETAIL_SYNTHESIZE_REJECT = "综合部经理审核不通过";

	/**破题状态 未破*/
	public static final Integer PO_NONE = 5000;
	/**破题状态 半破审核中*/
	public static final Integer PO_HALF_ING = 5001;
	/**破题状态 半破*/
	public static final Integer PO_HALF = 5002;
	/**破题状态 全破 审核中*/
	public static final Integer PO_ALL_ING = 5003;
	/**破题状态 全破*/
	public static final Integer PO_ALL = 5004;
	/**申请 内部项目结项 审核中*/
	public static final Integer INTERIOR_OVER_ING = 5005;
	/**内部项目结项 完成*/
	public static final Integer INTERIOR_OVER = 5006;
	/**申请外部结项 审核中*/
	public static final Integer EXTERIOR_OVER_ING  = 5007;
	/**外部结项 完成*/
	public static final Integer EXTERIOR_OVER  = 5008;








	
	
	
	
	
	
	
	
	
	
	
	
}

package com.yquick.simp.common;

/**
 * 常量类
 *
 * Created by YanKang on 2018年4月18日
 */
public class Constants {

	/**
	 * 请求成功
	 */
	public static final String SERVER_NORMAL_MSG = "请求成功";
	/**
	 * 服务异常
	 */
	public static final String SERVER_EXCEPTION_MSG = "服务异常";
	/**
	 * 数据库操作失败信息
	 */
	public static final String OPERATE_EXCEPTION_MSG = "操作失败";
	/**
	 * 数据库操作成功信息
	 */
	public static final String OPERATE_NORMAL_MSG = "操作成功";
	/**
	 * 请求成功代码:java
	 */
	public static final int SERVER_NORMAL_CODE = 0;
	/**
	 * 请求成功代码:lua
	 */
	public static final int SERVER_NORMAL_CODE_C = 200;
	/**
	 * 服务异常代码：500
	 */
	public static final int SERVER_EXCEPTION_CODE = 500;
	/**
	 * 数据库操作失败代码：503
	 */
	public static final int OPERATE_EXCEPTION_CODE = 503;
	/**
	 * Excel导出错误代码：505
	 */
	public static final Integer EXCEL_EXCEPTION_CODE = 505;
	/**
	 * 数据验证错误代码：506
	 */
	public static final int VALIDATION_EXCEPTION_CODE = 506;

}

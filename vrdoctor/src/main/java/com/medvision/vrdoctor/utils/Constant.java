package com.medvision.vrdoctor.utils;

/**
 * Created by cs on 2016/10/11.
 */

public class Constant {
	public static final String LOGIN_STATUS_SUCCESS = "0";//登陆成功
	public static final String LOGIN_STATUS_NETWORK_ERROR = "-1";//网络错误
	public static final String LOGIN_STATUS_PWD_ERROR = "-2";//密码错误
	public static final String LOGIN_STATUS_NO_USER = "-3";//无此用户
	public static final String LOGIN_STATUS_UNAUTHORIZED = "-4";//未认证
	public static final String LOGIN_STATUS_COMMITED = "-5";//已提交
	public static final String LOGIN_STATUS_UNPASSED = "-6";//未通过
	public static final String LOGIN_STATUS_CLOSE = "-7";//停诊

	public static final int VERIFY_STATUS_ING = 0;//医师认证 进行中
	public static final int VERIFY_STATUS_UNPASSED = 1;//医师认证 失败
}

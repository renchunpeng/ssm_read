package com.soecode.lyf.service;

import java.util.Map;

import com.soecode.lyf.entity.User;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface LoginService {

	/**
	 * 用户登录
	 *
	 * @return
	 */
	User doLogin(Map<String, String> map);

	/**
	 * 用户注册
	 *
	 * @return
	 */
	int register(User user);
}

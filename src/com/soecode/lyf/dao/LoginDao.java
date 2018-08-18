package com.soecode.lyf.dao;

import java.util.Map;

import com.soecode.lyf.entity.User;

public interface LoginDao {

	/**
	 * 用户登录查询
	 * @param map
	 * @return
	 */
	User doLogin(Map<String, String> map);

	int register(User user);
}

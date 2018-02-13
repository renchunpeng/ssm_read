package com.soecode.lyf.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.LoginDao;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginDao loginDao;

	@Override
	public User doLogin(Map<String, String> map) {
		// TODO 自动生成的方法存根
		return loginDao.doLogin(map);
	}
}

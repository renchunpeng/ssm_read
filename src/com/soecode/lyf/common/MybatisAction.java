package com.soecode.lyf.common;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class MybatisAction implements Interceptor {

	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object plugin(Object arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO 自动生成的方法存根
		
	}

}

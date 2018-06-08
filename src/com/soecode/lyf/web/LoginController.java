package com.soecode.lyf.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.common.Constants;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.LoginService;

@Controller
@RequestMapping("/login") // url:/模块/资源/{id}/细分 /seckill/list
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/goLogin", method = RequestMethod.GET)
	private String goLogin() {
		logger.info("进入登录接口！");
		
		return "/login/login";
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	private String doLogin(HttpServletRequest request, HttpServletResponse response, 
			String name, String pwd, HttpSession session) {

		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("pwd", pwd);
		User user = loginService.doLogin(map);
		
		if (null == user) {
			return "fail";
		}
		
		User loginUser = new User();
        BeanUtils.copyProperties(user,loginUser);
		session.setAttribute(Constants.SESSION_ID,loginUser);
		
		//填充cookie
        setCookie(request,response,name,pwd);
        
		return "success";
	}
	
    private void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String pwd){

        //cookie的保存暂时先不做吧
    }

}

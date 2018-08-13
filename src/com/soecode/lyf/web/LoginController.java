package com.soecode.lyf.web;

import com.soecode.lyf.businessUtils.BookListUtils;
import com.soecode.lyf.common.Constants;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.LoginService;
import com.soecode.lyf.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login") // url:/模块/资源/{id}/细分 /seckill/list
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private BookListUtils bookListUtils;

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
		bookListUtils.setBookListToCookie();
        
		return "success";
	}

	/**
	 * 将用户名和密码放入cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param pwd
	 */
    private void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String pwd){
        //用户名密码存入cookie
		Cookie userName=new Cookie(Constants.COOKIE_NAME,name);
		userName.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
		userName.setPath("/");
		response.addCookie(userName);

		Cookie userPwd=new Cookie(Constants.COOKIE_PWD,pwd);
		userPwd.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
		userPwd.setPath("/");
		response.addCookie(userPwd);
    }

}

package com.soecode.lyf.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.soecode.lyf.entity.User;

public class Common {
	public static String getLastDate(String str) {
		String result = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormat.parse(str.substring(str.indexOf("：")+1, str.length()));
			Date newDate = new Date();
			int cj = Integer.parseInt(String.valueOf((newDate.getTime()-date.getTime())/(1000*60)));
			
			if(cj <= 60){
				result = cj + "分钟前更新：";
			}else if(cj > 60 && cj <= 1440){
				result = cj/60 + "小时前更新:";
			}else {
				result = cj/1440 + "天前更新:";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		String str = "更新时间：2018-01-23 09:50:23";
		str = str.substring(str.indexOf("：")+1, str.length());
		System.out.println(str);
	}
	
	public static User getUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User loginUser = (User) WebUtils.getSessionAttribute(request,Constants.SESSION_ID);
		return loginUser;
	}
	
	public static HttpSession getSession(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession();
	}
}
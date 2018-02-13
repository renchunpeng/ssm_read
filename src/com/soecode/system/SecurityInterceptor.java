package com.soecode.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.soecode.lyf.entity.User;

/**
 * 对未登录用户进行拦截
 * 
 * @author rcp
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private String redirectUrl;
    
    /**
     * 不拦截的url
     */
    private List<String> excludes;

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * 可做权限校验
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
            throws IOException {
        String urlPath = request.getRequestURI();

        urlPath =  urlPath.replace("//","/").replace(request.getContextPath(), "");

        if (InterceptorUtil.lookupUrl(urlPath, excludes)) {
            return true;
        }

        User loginUser = (User) WebUtils.getSessionAttribute(request,"user");
        //从cookie恢复
        if(loginUser == null){
            autoLogin(request,response);
            loginUser = (User) WebUtils.getSessionAttribute(request,"user");
        }

        if(loginUser == null){
            response.sendRedirect(request.getContextPath() + redirectUrl );
            return false;
        }

        return true;
    }
    
    public User autoLogin(HttpServletRequest request,HttpServletResponse response){
    	return null;
    }

/*    //以前就只有 return null;
    public User autoLogin(HttpServletRequest request,HttpServletResponse response)  {
    	try {
        	Cookie[] cookie = request.getCookies();
        	String nameValue = null;
        	String pwdValue = null;
        	String uuidValue = null;
        	for (int i = 0; i < cookie.length; i++) {
    	    	Cookie cook = cookie[i];
    	    	if(cook.getName().equalsIgnoreCase(Constants.USER_COOKIE_KEY)){ //获取用戶名鍵
    	    		nameValue = DesUtil.decrypt(cook.getValue().toString(), Constants.DES_KEY);
    	    		System.out.println("name:"+nameValue);    //获取值 
    	    	}else if(cook.getName().equalsIgnoreCase(Constants.RAND_COOKIE_KEY)){ //获取密码键 
    	    		pwdValue = DesUtil.decrypt(cook.getValue().toString(), Constants.DES_KEY);
    	    		System.out.println("pwd:"+pwdValue);    //获取值 
    	    	}else if(cook.getName().equalsIgnoreCase("USERUUID")){ //获取uuid
   	    		 	uuidValue = DesUtil.decrypt(cook.getValue().toString(), Constants.DES_KEY);
   	    		 	System.out.println("uuid:"+uuidValue);    //获取值 
    	    	}
        	}
        	
        	User user = null;
        	if (uuidValue != null && uuidValue.length() > 0) {
        		user = loginService.loginUserByUUID(uuidValue);
			} else {
				user = loginService.loginUser(nameValue,pwdValue);
			}
            
            if(user != null){
            	HttpSession session = request.getSession();
            	User loginUser = new User();
                BeanUtils.copyProperties(user,loginUser);
                //设置是否超管
                loginUser.setAdmin(Constants.ADMIN.equals(nameValue));

                //填充前台权限
                session.setAttribute(Constants.SESSION_ID,loginUser);

                //填充cookie
                setCookie(request,response,nameValue,pwdValue,uuidValue);
                
                //登录时一次性设置所有菜单的操作权限
                if (!Constants.ADMIN.equals(nameValue)) {
                	session.setAttribute(Constants.MENU_OPERATE_POWER , loginService.setOperatePower(user.getUserId()));
                }
            }else{
            }
		} catch (Exception e) {
			// TODO: handle exception
		}

        return null;
    }
    
    //为了cookie免登陆新建的
    private void setCookie(HttpServletRequest request,HttpServletResponse response,String name, String pwd, String uuid){
    	response.setHeader("P3P","CP=CAO PSA OUR");
        if("0".equals(Constants.LOGIN_COOKIE)){
            Cookie userCookie = RequestUtils.getCookie(request, Constants.USER_COOKIE_KEY);
            Cookie randCookie = RequestUtils.getCookie(request, Constants.RAND_COOKIE_KEY);
            RequestUtils.deleteCookie(response,userCookie,null);
            RequestUtils.deleteCookie(response,randCookie,null);
            return;
        }

        *//**
         * Des加密
         *//*
        try {
            Cookie cookie = new Cookie(Constants.USER_COOKIE_KEY,DesUtil.encrypt(name, Constants.DES_KEY));
            cookie.setPath("/");
            cookie.setMaxAge(86400);//一天的秒数
            response.addCookie(cookie);

            cookie = new Cookie(Constants.RAND_COOKIE_KEY, DesUtil.encrypt(pwd, Constants.DES_KEY));
            cookie.setPath("/");
            cookie.setMaxAge(86400);//一天的秒数
            response.addCookie(cookie);
            
            if (uuid != null && uuid.length() >0) {
            	//新增的uuid
                cookie = new Cookie("USERUUID", DesUtil.encrypt(uuid, Constants.DES_KEY));
                cookie.setPath("/");
                cookie.setMaxAge(86400);//一天的秒数
                response.addCookie(cookie);
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

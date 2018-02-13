package com.soecode.system.tag.opt;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.factory.annotation.Configurable;

import com.soecode.lyf.common.Constants;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.User;

/**
 * 菜单操作权限标签：新增
 * @author HuangDong
 *
 */
@Configurable
public class ADD extends SimpleTagSupport {
    

    /**
     * 加载标签
     */
	public void doTag() throws JspException, IOException {
		//这里设置查询到用户没有登录直接就放开自定义标签中的内容猜测可能是为了照顾那些不需要登录就可以访问的页面
		//但是不需要登录就能访问的页面感觉也没必要用权限控制标签
    	User user = (User) getJspContext().getAttribute("user" , PageContext.SESSION_SCOPE);
        if (null == user) {
            getJspBody().invoke(null);
            return;
        }
        
        String menuOptPower = "";
        if (menuOptPower.indexOf("ADD") > -1 || "ALL".equals(menuOptPower)) {
            getJspBody().invoke(null);
            return;
        }
    }
}

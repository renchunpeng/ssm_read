package com.soecode.lyf.businessUtils;

import com.soecode.lyf.common.Common;
import com.soecode.lyf.common.Constants;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rcp on 2018/8/13.
 */
@Component
public class BookListUtils {
    @Autowired
    private MobileService mobileService;

    /**
     * 将用户书籍列表放入cookie(已经废弃，没地方用了)
     */
    public void setBookListToCookie(){
        User user = (User) Common.getSession().getAttribute(Constants.SESSION_ID);
        HttpServletResponse response = Common.getResponse();
        List<MyBook> lists = mobileService.getList(user.getId());
        String bookList = "";
        for (MyBook myBook : lists) {
            //拼接书架url
            bookList += myBook.getBookUrl() + "|";
        }
        Common.getSession().setAttribute(Constants.BOOK_LIST, bookList);

        //用户书籍列表cookie
        Cookie cookieBookList = new Cookie(Constants.BOOK_LIST,bookList);
        cookieBookList.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
        cookieBookList.setPath("/");
        response.addCookie(cookieBookList);
    }

    /**
     * 获取当前用户书籍列表
     * @return
     */
    public List<MyBook> getBookList(){
        User user = (User) Common.getSession().getAttribute(Constants.SESSION_ID);
        List<MyBook> lists = mobileService.getList(user.getId());

        return  lists;
    }

    /**
     * 获取书籍列表url拼接字符串
     * @return
     */
    public String getBookListToString(){
        User user = (User) Common.getSession().getAttribute(Constants.SESSION_ID);
        List<MyBook> lists = mobileService.getList(user.getId());
        String bookList = "";
        for (MyBook myBook : lists) {
            //拼接书架url
            bookList += myBook.getBookUrl() + "|";
        }

        return  bookList;
    }
}

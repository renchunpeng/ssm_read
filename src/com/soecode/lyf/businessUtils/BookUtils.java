package com.soecode.lyf.businessUtils;

import com.soecode.lyf.common.Common;
import com.soecode.lyf.common.Constants;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.service.BookService;
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
public class BookUtils {
    @Autowired
    private MobileService mobileService;
    
    @Autowired
    private BookService bookService;

    /**
     * 获取当前用户书籍列表
     * @return
     */
    public List<Book> getBookList(){
        List<Book> bookList = bookService.getBookList();

        return  bookList;
    }

    public String getBookListToString() {
        List<Book> bookList = bookService.getBookList();

        String books = "";
        for (Book book : bookList) {
            //拼接书架url
            books += book.getBookUrl() + "|";
        }

        return  books;
    }

}

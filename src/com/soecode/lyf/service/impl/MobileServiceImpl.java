package com.soecode.lyf.service.impl;

import com.soecode.lyf.businessUtils.BookListUtils;
import com.soecode.lyf.businessUtils.BookUtils;
import com.soecode.lyf.dao.BookDao;
import com.soecode.lyf.dao.MobileDao;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.UserAndBook;
import com.soecode.lyf.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MobileServiceImpl implements MobileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MobileDao mobileDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookUtils bookUtils;

    @Override
    public List<MyBook> getList(String userId) {
        return mobileDao.queryAll(userId);
    }

    @Override
    public void updatePageName(Map<String, String> map) {
        mobileDao.updatePageName(map);
    }

    @Override
    @Transactional
    public void addBook(UserAndBook book) {
        mobileDao.addBook(book);
        //判断该书籍在book中是否存在不存在就存入
        String books = bookUtils.getBookListToString();
        if (!books.contains(book.getBookUrl())) {
            Book item = new Book();
            item.setName(book.getName());
            item.setBookUrl(book.getBookUrl());
            item.setBookImg(book.getImgUrl());
            bookDao.saveBook(item);
        }

    }

    @Override
    public int saveBookMark(Map params) {
        return mobileDao.saveBookMark(params);
    }

    @Override
    @Transactional
    public int removeBookList(Map params) {
        int x = mobileDao.removeBookList(params);
        return x;
    }
}

package com.soecode.lyf.service.impl;

import com.soecode.lyf.common.Common;
import com.soecode.lyf.common.Constants;
import com.soecode.lyf.dao.MobileDao;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.UserAndBook;
import com.soecode.lyf.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MobileServiceImpl implements MobileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MobileDao mobileDao;

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

        List<MyBook> lists = new ArrayList<MyBook>();
        lists = mobileDao.queryAll(Common.getUser().getId());
        String bookList = "";

        //获取最后更新时间和最新章节
        for (MyBook myBook : lists) {
            //拼接书架url
            bookList += myBook.getBookUrl() + "|";
        }
        Common.getSession().setAttribute(Constants.BOOK_LIST, bookList);
    }
}

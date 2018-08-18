package com.soecode.lyf.service.impl;

import com.soecode.lyf.dao.BookDao;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}

	@Override
	public int saveBook(Book book) {
		return bookDao.saveBook(book);
	}
}

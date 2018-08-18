package com.soecode.lyf.service;

import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.User;

import java.util.List;
import java.util.Map;

public interface BookService {

	/**
	 * 获取book列表
	 *
	 * @return
	 */
	List<Book> getBookList();

	int saveBook(Book book);

}

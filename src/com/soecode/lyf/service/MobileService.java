package com.soecode.lyf.service;

import java.util.List;
import java.util.Map;

import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.UserAndBook;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface MobileService {

	/**
	 * 查询用户书架
	 * 
	 * @return
	 */
	List<MyBook> getList(String userId);
	
	/**
	 * 修改用户最后看到的章节名
	 * 
	 * @param id
	 */
	void updatePageName(Map<String, String> map);
	
	/**
	 * 将书籍添加到书架
	 * 
	 * @param id
	 */
	void addBook(UserAndBook book);

	/**
	 * 保存书签
	 * @param params
	 */
	int saveBookMark(Map params);

	/**
	 * 移除书籍
	 * @param params
	 */
	int removeBookList(Map params);
}

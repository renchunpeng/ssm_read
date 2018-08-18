package com.soecode.lyf.dao;

import java.util.List;
import java.util.Map;

import com.soecode.lyf.entity.MyBook;
import com.soecode.lyf.entity.UserAndBook;

public interface MobileDao {

	/**
	 * 查询所有图书
	 * 
	 * @param offset 查询起始位置
	 * @param limit 查询条数
	 * @return
	 */
	List<MyBook> queryAll(String userId);
	
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
	 * 移除书籍列表
	 * @param params
	 * @return
	 */
	int removeBookList(Map params);
}

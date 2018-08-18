package com.soecode.lyf.entity;

/**
 * 图书实体
 */
public class Book {

	private String bookId;// 图书ID

	private String name;// 图书名称

	private String bookUrl;// 书籍地址

	private String bookImg;// 书籍图片地址

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
}

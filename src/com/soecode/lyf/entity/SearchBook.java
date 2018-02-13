package com.soecode.lyf.entity;

public class SearchBook{
	//书名
	private String bookName;
	
	//书籍地址
	private String bookUrl;
	
	//书籍图片地址
	private String bookPicUrl;
	
	//分类
	private String type;
	
	//作者
	private String auth;
	
	//最新章节名称
	private String latestPageName;
	
	//最新章节地址
	private String latestPageUrl;
	
	//简介
	private String remark;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}


	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getBookPicUrl() {
		return bookPicUrl;
	}

	public void setBookPicUrl(String bookPicUrl) {
		this.bookPicUrl = bookPicUrl;
	}

	public String getLatestPageName() {
		return latestPageName;
	}

	public void setLatestPageName(String latestPageName) {
		this.latestPageName = latestPageName;
	}

	public String getLatestPageUrl() {
		return latestPageUrl;
	}

	public void setLatestPageUrl(String latestPageUrl) {
		this.latestPageUrl = latestPageUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
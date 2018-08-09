package com.soecode.lyf.entity;


public class MyBook{
	private String id;//主键
	
	private String name;//书名
	
	private String bookUrl;//书籍地址
	
	private String imgUrl;//书籍封面图片地址
	
	private String lastUpdateDate;//最后更新时间
	
	private String lastPageName;//最后章节名
	
	private boolean update;//是否更新最新章节

	private String bookMark;//书签

	public String getBookMark() {
		return bookMark;
	}

	public void setBookMark(String bookMark) {
		this.bookMark = bookMark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastPageName() {
		return lastPageName;
	}

	public void setLastPageName(String lastPageName) {
		this.lastPageName = lastPageName;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
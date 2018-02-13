package com.soecode.lyf.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.entity.Book;
import com.soecode.lyf.service.BookService;
import com.soecode.lyf.service.impl.BookServiceImpl;
import com.soecode.lyf.service.impl.MobileServiceImpl;

@Controller
@RequestMapping("/test") // url:/模块/资源/{id}/细分 /seckill/list
public class TestController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private List<Book> list(Model model) {
		logger.info("查询开始！");
		List<Book> list = bookService.getList();
		return list;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> insertBook(){
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			Book book = new Book();
			book.setName("jquery");
			book.setNumber(123);
			bookService.insertBook(book);
			
			map.put("flag", "success");
			map.put("msg", "成功");
		} catch (Exception e) {
			map.put("flag", "fail");
			map.put("msg", e.getMessage());
			
		}
		return map;
	}

	@Test
	public void rcp(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		
		BookServiceImpl bean = context.getBean("bookServiceImpl",BookServiceImpl.class);
		Book book = new Book();
		book.setName("jquery");
		book.setNumber(123);
		bean.insertBook(book);
	}

}

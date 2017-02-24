package net.xinqushi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.xinqushi.dao.BookDAO;
import net.xinqushi.pojo.Book;

@Controller
@RequestMapping("/book")
public class BookController {
	// 注入dao
	@Autowired
	private BookDAO bookDAO;
//	@Resource
//	public void setBookDAO(BookDAO bookDAO) {
//		this.bookDAO = bookDAO;
//	}

	@RequestMapping("/get.do") // 配置二级引用路径	
	@ResponseBody // 配置服务器端将对象按json方式返回
	public ArrayList<Book> find(String pattern) {
		ArrayList<Book> list = null;
		// 加%可提供模糊查询功能
		try {
			list =  bookDAO.find("%" + pattern + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/find.do")
	public String find(){
		return "welcome";
	}
	
	@RequestMapping(value = "/test.do")
	public String test(String query,Model model){
		ArrayList<Book> list = null;
		query = "";
		// 加%可提供模糊查询功能
		try {
			list =  bookDAO.find("%" + query + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("BOOKS", list);
		return "test";
	}
	
	@RequestMapping("/{pattern}/get2.do") // 配置二级引用路径
//	@ResponseBody // 配置服务器端将对象按json方式返回
	public String find(@PathVariable String pattern,Model model) {
		ArrayList<Book> list = null;
		// 加%可提供模糊查询功能
		try {
			list =  bookDAO.find("%" + pattern + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("BOOKS", list);
		return "test";
	}
}

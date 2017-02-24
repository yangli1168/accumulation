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
	// ע��dao
	@Autowired
	private BookDAO bookDAO;
//	@Resource
//	public void setBookDAO(BookDAO bookDAO) {
//		this.bookDAO = bookDAO;
//	}

	@RequestMapping("/get.do") // ���ö�������·��	
	@ResponseBody // ���÷������˽�����json��ʽ����
	public ArrayList<Book> find(String pattern) {
		ArrayList<Book> list = null;
		// ��%���ṩģ����ѯ����
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
		// ��%���ṩģ����ѯ����
		try {
			list =  bookDAO.find("%" + query + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("BOOKS", list);
		return "test";
	}
	
	@RequestMapping("/{pattern}/get2.do") // ���ö�������·��
//	@ResponseBody // ���÷������˽�����json��ʽ����
	public String find(@PathVariable String pattern,Model model) {
		ArrayList<Book> list = null;
		// ��%���ṩģ����ѯ����
		try {
			list =  bookDAO.find("%" + pattern + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("BOOKS", list);
		return "test";
	}
}

package net.xinqushi.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.xinqushi.mapper.BookMapper;
import net.xinqushi.pojo.Book;

@Component
public class BookDAO {
	// ×¢Èëmapper
//	 @Autowired
	private BookMapper bookMapper;

	@Resource
	public void setBookMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	public ArrayList<Book> find(String pattern) throws IOException {

		ArrayList<Book> list = bookMapper.find(pattern);
		return list;
	}
}

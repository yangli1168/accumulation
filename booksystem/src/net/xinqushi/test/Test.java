package net.xinqushi.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import net.xinqushi.common.MybatisUtils;
import net.xinqushi.mapper.BookMapper;
import net.xinqushi.mapper.UserMapper;
import net.xinqushi.pojo.Book;
import net.xinqushi.pojo.User;

public class Test {
	@org.junit.Test
	public void test() throws IOException{
		SqlSession session = MybatisUtils.openSession();
		BookMapper bookMapper=session.getMapper(BookMapper.class);
		String pattern = "大";
		ArrayList<Book> list=bookMapper.find("%" + pattern + "%");
		session.close();
		for (Book book : list) {
			System.out.println(book.getName() + "--" + book.getPublisher());
		}
	}
	
	@org.junit.Test
	public void test2() throws IOException{
		SqlSession session = MybatisUtils.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setName("梦璃");
		user.setPwd("123");
		System.out.println(mapper.isExists(user) > 0 ? "有此用户":"无此用户");
	}
}

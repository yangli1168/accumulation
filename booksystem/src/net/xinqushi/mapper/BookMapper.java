package net.xinqushi.mapper;

import java.util.ArrayList;

import net.xinqushi.pojo.Book;

public interface BookMapper {
	public ArrayList<Book> find(String pattern);
}

package net.xinqushi.service;

import net.xinqushi.pojo.User;

public interface UserService {
	public void add(User user);
	
	public boolean checkExists(User user);
	
	public User get(User user);
}

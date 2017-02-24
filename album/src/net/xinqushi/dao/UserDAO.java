package net.xinqushi.dao;

import net.xinqushi.pojo.User;

public interface UserDAO {
	//保存或更新
	public void save(User user);

	//判断用户是否存在
	public boolean isExists(User user);
	
	//从数据库获取用户
	public User getUser(User user);
}

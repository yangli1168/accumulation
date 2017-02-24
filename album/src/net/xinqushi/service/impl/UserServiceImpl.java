package net.xinqushi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import net.xinqushi.dao.UserDAO;
import net.xinqushi.pojo.User;
import net.xinqushi.service.UserService;
@Service
@Scope(value = "singleton")
public class UserServiceImpl implements UserService {
	//注入userdao
	@Autowired
	private UserDAO uDao;
	
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		uDao.save(user);
	}

	@Override
	public boolean checkExists(User user) {
		// TODO Auto-generated method stub
		return uDao.isExists(user);
	}

	@Override
	public User get(User user) {
		// TODO Auto-generated method stub
		return uDao.getUser(user);
	}
	
}

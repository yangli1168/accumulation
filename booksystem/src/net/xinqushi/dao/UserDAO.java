package net.xinqushi.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.xinqushi.mapper.UserMapper;
import net.xinqushi.pojo.User;

@Component
public class UserDAO {
	// ע��mapper
//	 @Autowired
	private UserMapper userMapper;

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	// ����û�
	public boolean isValid(User user) {
		boolean flag = false;
		// �ж�
		if (userMapper.isExists(user) > 0) {
			flag = true;
		}
		//
		return flag;
	}

}

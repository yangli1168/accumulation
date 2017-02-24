package net.xinqushi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import net.xinqushi.dao.UserDAO;
import net.xinqushi.pojo.User;
@Component
public class UserDAOImpl implements UserDAO {
	// 引入HibernateTemplate并设置setter【因为spring只用到setter】
	@Autowired
	HibernateTemplate hibernateTemplate;

	/*
	 * @Resource //配置注入方式 public void setHibernateTemplate(HibernateTemplate
	 * hibernateTemplate) { this.hibernateTemplate = hibernateTemplate; }
	 */

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.merge(user);
	}

	@Override
	public boolean isExists(User user) {
		// TODO Auto-generated method stub
		return hibernateTemplate.findByExample(user).size() > 0;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		List<User> list = hibernateTemplate.findByExample(user);
		// 判断：list为空时返回null
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

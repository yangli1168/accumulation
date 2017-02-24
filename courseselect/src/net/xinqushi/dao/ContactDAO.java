package net.xinqushi.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.pojo.Contact;

public class ContactDAO {
	public int update(Contact contact) {
		// 开启session
		Session session = HibernateUtil.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 事务的数据库操作
		session.saveOrUpdate(contact);
		// 获取contact的id
		int id = contact.getId();
		// 提交事务
		tx.commit();
		// 关闭session
		session.close();
		// 返回id
		return id;
	}
}

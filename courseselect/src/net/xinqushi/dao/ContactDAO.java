package net.xinqushi.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.pojo.Contact;

public class ContactDAO {
	public int update(Contact contact) {
		// ����session
		Session session = HibernateUtil.openSession();
		// ��������
		Transaction tx = session.beginTransaction();
		// ��������ݿ����
		session.saveOrUpdate(contact);
		// ��ȡcontact��id
		int id = contact.getId();
		// �ύ����
		tx.commit();
		// �ر�session
		session.close();
		// ����id
		return id;
	}
}

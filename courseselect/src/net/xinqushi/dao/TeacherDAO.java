package net.xinqushi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.common.Pager;
import net.xinqushi.common.QueryUtil;
import net.xinqushi.pojo.Teacher;

public class TeacherDAO {
	// ��������
	public void update(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(teacher);
		closeSession(session);
	}

	private void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	// ɾ��
	public void delete(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(teacher);
		closeSession(session);
	}

	/** ��ѯteacher by id */
	public Teacher getTeacherById(Teacher teacher) {
		String hql = "from Teacher where id =:id";
		return (Teacher) QueryUtil.getInfo(hql, teacher);
	}

	/** ��ѯteacher���� */
	public ArrayList<Teacher> getTeachers() {
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<Teacher> list = (ArrayList<Teacher>) query.list();
		closeSession(session);
		return list;
	}

	/** ��ҳ����ѯ */
	public void initPageCount(Pager pager){
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Teacher> list=query.list();
		//��ҳ������
		//1���������ܼ�¼��/ÿҳ��¼��
		pager.setPageCount(list.size()/pager.getPageSize());
		//���ܼ�¼����/ÿҳ��¼��������ʱ��Ӧ+1ҳ
		if (list.size() % pager.getPageSize() > 0) {
			pager.setPageCount(list.size()/pager.getPageSize() + 1);
		}
		//�ر�session
		closeSession(session);		
	}
	/** ��ҳ��Ϣ��ѯ */
	public List<Teacher> getTeachers(Pager pager){
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		//��ҳ����
		//1������һҳ�ӵڼ������ݿ�ʼ
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//2������һҳ��ʾ��������¼
		query.setMaxResults(pager.getPageSize());
		//ִ�в�ѯ
		@SuppressWarnings("unchecked")
		List<Teacher> list = query.list();
		closeSession(session);
		return list;
	}
}

package net.xinqushi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.common.Pager;
import net.xinqushi.common.QueryUtil;
import net.xinqushi.pojo.Course;

public class CourseDAO {
	// ��������
	public void update(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.saveOrUpdate(course);
		closeSession(session);
	}

	private void closeSession(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	// ɾ��
	public void delete(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(course);
		closeSession(session);
	}

	/** ��ѯcourse by id */
	public Course getCourseById(Course course) {
		String hql = "from Course where id =:id";
		return (Course) QueryUtil.getInfo(hql, course);
	}

	/** ��ѯcourse���� */
	public ArrayList<Course> getCourses() {
		String hql = "from Course";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<Course> list = (ArrayList<Course>) query.list();
		closeSession(session);
		return list;
	}

	/** ��ҳ����ѯ */
	public void initPageCount(Pager pager){
		String hql = "from Course";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list=query.list();
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
	public List<Course> getCourses(Pager pager){
		String hql = "from Course";
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
		List<Course> list = query.list();
		closeSession(session);
		return list;
	}
}

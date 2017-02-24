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
	// 保存或更新
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

	// 删除
	public void delete(Teacher teacher) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(teacher);
		closeSession(session);
	}

	/** 查询teacher by id */
	public Teacher getTeacherById(Teacher teacher) {
		String hql = "from Teacher where id =:id";
		return (Teacher) QueryUtil.getInfo(hql, teacher);
	}

	/** 查询teacher集合 */
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

	/** 分页数查询 */
	public void initPageCount(Pager pager){
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Teacher> list=query.list();
		//分页数设置
		//1、先设置总记录数/每页记录数
		pager.setPageCount(list.size()/pager.getPageSize());
		//当总记录条数/每页记录数有余数时，应+1页
		if (list.size() % pager.getPageSize() > 0) {
			pager.setPageCount(list.size()/pager.getPageSize() + 1);
		}
		//关闭session
		closeSession(session);		
	}
	/** 分页信息查询 */
	public List<Teacher> getTeachers(Pager pager){
		String hql = "from Teacher";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		//分页设置
		//1、设置一页从第几条数据开始
		query.setFirstResult((pager.getPageNo()-1)*pager.getPageSize());
		//2、设置一页显示多少条记录
		query.setMaxResults(pager.getPageSize());
		//执行查询
		@SuppressWarnings("unchecked")
		List<Teacher> list = query.list();
		closeSession(session);
		return list;
	}
}

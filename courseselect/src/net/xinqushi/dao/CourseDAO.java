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
	// 保存或更新
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

	// 删除
	public void delete(Course course) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.delete(course);
		closeSession(session);
	}

	/** 查询course by id */
	public Course getCourseById(Course course) {
		String hql = "from Course where id =:id";
		return (Course) QueryUtil.getInfo(hql, course);
	}

	/** 查询course集合 */
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

	/** 分页数查询 */
	public void initPageCount(Pager pager){
		String hql = "from Course";
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list=query.list();
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
	public List<Course> getCourses(Pager pager){
		String hql = "from Course";
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
		List<Course> list = query.list();
		closeSession(session);
		return list;
	}
}

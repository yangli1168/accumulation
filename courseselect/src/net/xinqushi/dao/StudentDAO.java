package net.xinqushi.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.xinqushi.common.HibernateUtil;
import net.xinqushi.common.QueryUtil;
import net.xinqushi.pojo.Student;

public class StudentDAO {
	// 增
	public void update(Student student) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		closeSession(session, tx);
	}

	private void closeSession(Session session, Transaction tx) {
		tx.commit(); // 可采用"session.getTransaction().commit();"替换
		session.close();
	}

	//
	public Student check2(Student student) {
		Session session = HibernateUtil.openSession();
		String hql = "from Student where name=:name and pwd=:pwd";
		Query query = session.createQuery(hql);
		// 传值方法一
		query.setString("name", student.getName());
		query.setString("pwd", student.getPwd());
		// 传值方法二
		// query.setProperties(student);
		// 获取集合
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		// 将student置为空
		student = null;
		// 判断集合
		if (list.size() > 0) {
			student = list.get(0);
		}
		// 关闭session
		session.close();
		// 返回
		return student;
	}

	/** by name , pwd */
	public Student check(Student student) {
		String hql = "from Student where name=:name and pwd=:pwd";
		return (Student) QueryUtil.getInfo(hql, student);
	}

	/** by id , pwd */
	public boolean checkPwd(Student student) {
		String hql = "from Student where id=:id and pwd=:pwd";
		student = (Student) QueryUtil.getInfo(hql, student);
		// 判定
		return student == null ? false : true;
	}

	/** by id */
	public String getUrl(Student student) {
		String hql = "select photo from Student where id=:id";
		return (String) QueryUtil.getInfo(hql, student);
	}

	/** by id */
	public Student get(Student student) {
		String hql = "from Student where id=:id";
		student = (Student) QueryUtil.getInfo(hql, student);
		//返回
		return student;
	}
	
	//查询学生信息byId
	public Student getStudentById(Student student){
		Session session = HibernateUtil.openSession();
		String hql = "from Student where id=:id";
		session.beginTransaction();
		Query query=session.createQuery(hql)
				.setProperties(student);
		//获取list集合
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();		
		student=list.get(0);
		//初始化课程信息[hibernate懒汉机制，延迟加载]
		Hibernate.initialize(student.getCourses());
		session.getTransaction().commit();
		session.close();
		return student;
	}
}

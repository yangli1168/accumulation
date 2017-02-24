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
	// ��
	public void update(Student student) {
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		closeSession(session, tx);
	}

	private void closeSession(Session session, Transaction tx) {
		tx.commit(); // �ɲ���"session.getTransaction().commit();"�滻
		session.close();
	}

	//
	public Student check2(Student student) {
		Session session = HibernateUtil.openSession();
		String hql = "from Student where name=:name and pwd=:pwd";
		Query query = session.createQuery(hql);
		// ��ֵ����һ
		query.setString("name", student.getName());
		query.setString("pwd", student.getPwd());
		// ��ֵ������
		// query.setProperties(student);
		// ��ȡ����
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		// ��student��Ϊ��
		student = null;
		// �жϼ���
		if (list.size() > 0) {
			student = list.get(0);
		}
		// �ر�session
		session.close();
		// ����
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
		// �ж�
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
		//����
		return student;
	}
	
	//��ѯѧ����ϢbyId
	public Student getStudentById(Student student){
		Session session = HibernateUtil.openSession();
		String hql = "from Student where id=:id";
		session.beginTransaction();
		Query query=session.createQuery(hql)
				.setProperties(student);
		//��ȡlist����
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();		
		student=list.get(0);
		//��ʼ���γ���Ϣ[hibernate�������ƣ��ӳټ���]
		Hibernate.initialize(student.getCourses());
		session.getTransaction().commit();
		session.close();
		return student;
	}
}

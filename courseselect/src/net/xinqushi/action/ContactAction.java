package net.xinqushi.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.dao.ContactDAO;
import net.xinqushi.dao.StudentDAO;
import net.xinqushi.pojo.Contact;
import net.xinqushi.pojo.Student;

public class ContactAction extends ActionSupport {
	private StudentDAO sDao = new StudentDAO();
	private ContactDAO cDao = new ContactDAO();
	// ��ȡ�����͹����Ķ�����Ϣ
	private Student student;
	private Contact contact;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	// ��������
	public String updateContact() {
		// ��contact�������ݿ�
		cDao.update(contact);
		// �����ݿ��ȡstudent������Ϣ�������͹����Ŀ��ܲ�������
		student = sDao.get(student);
		// ������contact�Ĺ�ϵ��������ݿ�
		student.setContact(contact);
		sDao.update(student);
		// ����session�е�student��Ϣ��������caontact��
		ActionContext.getContext().getSession().put("LOGINSTUDENT", student);
		// ������ҳ
		return "main";
	}
}

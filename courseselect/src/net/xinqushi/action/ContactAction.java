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
	// 获取请求传送过来的对象信息
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

	// 保存或更新
	public String updateContact() {
		// 将contact存入数据库
		cDao.update(contact);
		// 从数据库获取student完整信息【请求传送过来的可能不完整】
		student = sDao.get(student);
		// 设置与contact的关系后更新数据库
		student.setContact(contact);
		sDao.update(student);
		// 更新session中的student信息【将包含caontact】
		ActionContext.getContext().getSession().put("LOGINSTUDENT", student);
		// 返回主页
		return "main";
	}
}

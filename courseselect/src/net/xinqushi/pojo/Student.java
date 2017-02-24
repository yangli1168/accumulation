package net.xinqushi.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {
	private int id;
	private String name;
	private String pwd;
	private String sex;
	private String phone;
	private String grade;
	private String photo;
	// 与contact一对一关系
	private Contact contact;
	// 与course多对多关系
	private Set<Course> courses = new HashSet<Course>();

	@ManyToMany
	@JoinTable(name = "student_course", 
				joinColumns = @JoinColumn(name = "sid"), 
				inverseJoinColumns = @JoinColumn(name = "cid"))
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@OneToOne
	@JoinColumn(name = "cid", unique = true)
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex + ", phone=" + phone
				+ ", grade=" + grade + ", photo=" + photo + ", contact=" + contact + ", courses=" + courses + "]";
	}

}

package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teacher {
	private int id;
	private String name;
	private String sex;
	private String phone;
//	private Set<Course> courses=new HashSet<Course>();
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}
//	public void setCourses(List<Course> courses) {
//		this.courses = courses;
//	}
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
	
//  	public Set<Course> getCourses() {
//		return courses;
//	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", courses=" + courses
				+ "]";
	}
	
	//实际使用中不会使用老师来设置多门课程，因此注释。
//	public void setCourses(Set<Course> courses) {
//		this.courses = courses;
//	}	
	
	
}

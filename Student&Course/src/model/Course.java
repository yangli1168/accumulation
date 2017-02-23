package model;

import java.util.List;

public class Course {
	private int id;
	private String name;
	private String type;
	private String hours;
	private Teacher teacher;
	private List<Student> students;
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudents() {
		return students;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", type=" + type + ", hours=" + hours + ", teacher=" + teacher
				+ ", students=" + students + "]";
	}
		
}
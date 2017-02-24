package net.xinqushi.test;

import net.xinqushi.dao.RoomDAO;
import net.xinqushi.dao.StudentDAO;
import net.xinqushi.dao.TeacherDAO;
import net.xinqushi.pojo.Room;
import net.xinqushi.pojo.Student;
import net.xinqushi.pojo.Teacher;

public class Test {
	@org.junit.Test
	public void testAdd(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setName("梦璃");
		student.setGrade("三年级");
		student.setPwd("123456");
		student.setPhone("1355555");
		student.setPhoto("http://59.110.16.27:8088/image-web/upload/qq.jpg");
		//使用dao存入数据库
		new StudentDAO().update(student);
	}
	
	@org.junit.Test
	public void testAdd2(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setName("test");
		student.setGrade("三年级");
		student.setSex("女");
		student.setPwd("123456");
		student.setPhone("1355555");
		student.setPhoto("http://59.110.16.27:8088/image-web/upload/qq.jpg");
		//使用dao存入数据库
		new StudentDAO().update(student);
	}
	
	@org.junit.Test
	public void testGet(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setName("菱纱");
		student.setPwd("123456");
		//调用dao查询数据库
//		student = new StudentDAO().check(student);
		student = new StudentDAO().check2(student);
		System.out.println(student);
	}
	
	@org.junit.Test
	public void testCheckPwd(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setId(2);
		student.setPwd("1234");
		//调用dao查询数据库
		boolean flag = new StudentDAO().checkPwd(student);
		System.out.println(flag);
	}
	
	@org.junit.Test
	public void testGetUrl(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setId(2);
		//调用dao查询数据库
		System.out.println(new StudentDAO().getUrl(student));
	}
	
	@org.junit.Test
	public void testGetStudent(){
		//初始化student对象并补充信息
		Student student = new Student();
		student.setId(2);
		//调用dao查询数据库
		System.out.println(new StudentDAO().get(student));
	}
	
	@org.junit.Test
	public void testGetTeacher(){
		Teacher teacher = new Teacher();
		teacher.setId(4);
		System.out.println(new TeacherDAO().getTeacherById(teacher));
	}
	
	@org.junit.Test
	public void testGetRoom(){
		Room room = new Room();
		room.setId(4);
		System.out.println(new RoomDAO().getRoomById(room));
	}
}

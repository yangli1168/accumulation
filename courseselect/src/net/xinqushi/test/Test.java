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
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setName("����");
		student.setGrade("���꼶");
		student.setPwd("123456");
		student.setPhone("1355555");
		student.setPhoto("http://59.110.16.27:8088/image-web/upload/qq.jpg");
		//ʹ��dao�������ݿ�
		new StudentDAO().update(student);
	}
	
	@org.junit.Test
	public void testAdd2(){
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setName("test");
		student.setGrade("���꼶");
		student.setSex("Ů");
		student.setPwd("123456");
		student.setPhone("1355555");
		student.setPhoto("http://59.110.16.27:8088/image-web/upload/qq.jpg");
		//ʹ��dao�������ݿ�
		new StudentDAO().update(student);
	}
	
	@org.junit.Test
	public void testGet(){
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setName("��ɴ");
		student.setPwd("123456");
		//����dao��ѯ���ݿ�
//		student = new StudentDAO().check(student);
		student = new StudentDAO().check2(student);
		System.out.println(student);
	}
	
	@org.junit.Test
	public void testCheckPwd(){
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setId(2);
		student.setPwd("1234");
		//����dao��ѯ���ݿ�
		boolean flag = new StudentDAO().checkPwd(student);
		System.out.println(flag);
	}
	
	@org.junit.Test
	public void testGetUrl(){
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setId(2);
		//����dao��ѯ���ݿ�
		System.out.println(new StudentDAO().getUrl(student));
	}
	
	@org.junit.Test
	public void testGetStudent(){
		//��ʼ��student���󲢲�����Ϣ
		Student student = new Student();
		student.setId(2);
		//����dao��ѯ���ݿ�
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

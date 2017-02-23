package test;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import model.Course;
import model.CourseMapper;
import model.Student;
import model.StudentCourse;
import model.StudentCourseMapper;
import model.StudentMapper;
import utility.MybatisUtils;

public class TestManyToMany {

	@Test
	public void testSelect() throws IOException {
		SqlSession session=MybatisUtils.openSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		Student student=studentMapper.get(5);
		System.out.println(student);
		System.out.println(student.getCourses());
		
		System.out.println("*******************************");
		CourseMapper courseMapper=session.getMapper(CourseMapper.class);
		Course course=courseMapper.get(3);
		System.out.println(course);	
		System.out.println(course.getStudents());	
	}
	
	//课程、学生、老师的多表关联查询-2016.7.1
	@Test
	public void testSelect2() throws IOException {
		SqlSession session=MybatisUtils.openSession();
		
		System.out.println("*******************************");
		CourseMapper courseMapper=session.getMapper(CourseMapper.class);
		Course course=courseMapper.gets(3);
		System.out.println(course);	
		System.out.println(course.getStudents());	
	}
	
	@Test
	public void testAdd() throws IOException
	{
		SqlSession session=MybatisUtils.openSession();
		StudentCourseMapper mapper=session.getMapper(StudentCourseMapper.class);
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		CourseMapper courseMapper=session.getMapper(CourseMapper.class);
		StudentCourse sc=new StudentCourse();
		//设置学生的一门课程
		sc.setStudent(studentMapper.get(5));
		sc.setCourse(courseMapper.get(3));
		mapper.add(sc);
		
		//设置学生的第二门课程
		sc=new StudentCourse();
		sc.setStudent(studentMapper.get(5));
		sc.setCourse(courseMapper.get(2));
		mapper.add(sc);
				
		session.commit();
		session.close();	
	}
	
	@Test
	public void testDelete() throws IOException
	{
		SqlSession session=MybatisUtils.openSession();
		StudentCourseMapper mapper=session.getMapper(StudentCourseMapper.class);
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		CourseMapper courseMapper=session.getMapper(CourseMapper.class);
		StudentCourse sc=new StudentCourse();
		//删除学生的一门课程
		sc.setStudent(studentMapper.get(5));
		sc.setCourse(courseMapper.get(3));
		mapper.delete(sc);
		
		//删除学生的第二门课程
		sc=new StudentCourse();
		sc.setStudent(studentMapper.get(5));
		sc.setCourse(courseMapper.get(2));
		mapper.delete(sc);
				
		session.commit();
		session.close();	
	}

}

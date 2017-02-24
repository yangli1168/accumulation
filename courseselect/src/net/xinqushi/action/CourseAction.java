package net.xinqushi.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.common.BtnBuilder;
import net.xinqushi.common.Pager;
import net.xinqushi.dao.CourseDAO;
import net.xinqushi.dao.RoomDAO;
import net.xinqushi.dao.StudentDAO;
import net.xinqushi.dao.TeacherDAO;
import net.xinqushi.pojo.Course;
import net.xinqushi.pojo.Room;
import net.xinqushi.pojo.Student;
import net.xinqushi.pojo.Teacher;

public class CourseAction extends ActionSupport {
	private CourseDAO cDao = new CourseDAO();
	private TeacherDAO tDao = new TeacherDAO();
	private RoomDAO rDao = new RoomDAO();
	private StudentDAO sDao = new StudentDAO();
	// ��ȡ������Ϣ
	private Course course;
	private int page;
	private Teacher teacher;
	private Room room;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ���������
	public String update() {
		return updateIt();
	}

	// Ϊ����ṩ�޸ķ���
	public String modify() {
		return updateIt();
	}

	// ɾ��
	public String delete() {
		cDao.delete(course);
		return null;
	}

	private String updateIt() {
		// �ж����?�޸ġ����ʱcourse.idΪ0���޸�ʱ>0��
		if (course.getId() == 0) {
			ActionContext.getContext().put("ADD", true);
		}
		// �����ݿ��������
		cDao.update(course);
		return dataForCourses();
	}

	// Ϊҳ��׼����ҳ����
	public String dataForCourses() {
		// ����request
		HttpServletRequest request = ServletActionContext.getRequest();
		// ����pager����ʼ����ҳ��
		Pager pager = new Pager();
		// ���÷���׼������
		dataPrepare(pager);
		// ��������·��
		String url = "/course/dataForCourses";
		// ������������session
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		// ����̨��ӡstr[�����]
		// System.out.println(str.toString());
		return "coursemain";
	}

	private void dataPrepare(Pager pager) {
		// ���ó�ʼҳ�롾����ҳδ����ʱ��
		if (page == 0) {
			page = 1;
		}
		cDao.initPageCount(pager);
		// ��ҳ�洫�ݵ�ҳ����������ж�
		if (page > pager.getPageCount()) {
			page = pager.getPageCount();
		}
		// �ж϶�course�Ĳ������޸Ļ�������������ʱ���ݿ��¼�����ӣ�pageӦ�ö�λ������ҳ�桿
		if (null != ActionContext.getContext().get("ADD")) {
			page = pager.getPageCount();
		}
		// ������Ҫ��ʾ��ҳ��
		pager.setPageNo(page);
		List<Course> list = cDao.getCourses(pager);
		// �ж�
		if (list.size() > 0) {
			// ����ҳ������Ϣ����session
			ActionContext.getContext().getSession().put("COURSES", list);
			// �����н��ҡ���ʦ��Ϣ����session
			ActionContext.getContext().getSession().put("ROOMS", rDao.getRooms());
			ActionContext.getContext().getSession().put("TEACHERS", tDao.getTeachers());
		}
	}

	// Ϊ�޸Ĳ���ṩ����
	public String getCourseById() {
		course = cDao.getCourseById(course);
		// �����jspҳ�洫����������
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// ��ҳ�淢����Ϣ
		try {
			ServletActionContext.getResponse().getWriter()
					.print(course.getId() + "!" + course.getName() + "!" + course.getType() + "!" + course.getHours());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Ϊinit.jspҳ��׼����ʾ���ݡ���ҳ��
	public String initMain() {
		// ����request
		HttpServletRequest request = ServletActionContext.getRequest();
		// ����pager����ʼ����ҳ��
		Pager pager = new Pager();
		dataPrepare(pager);
		// ��������·��
		String url = "/course/initMain";
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		return "initmain";
	}

	// Ϊchoose.jspҳ��׼����ʾ���ݡ���ҳ��
	public String chooseMain() {
		// ����request
		HttpServletRequest request = ServletActionContext.getRequest();
		// ����pager����ʼ����ҳ��
		Pager pager = new Pager();
		dataPrepare(pager);
		// ��������·��
		String url = "/course/chooseMain";
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		return "choosemain";
	}

	// initҳ����½�ʦ��Ϣ
	public String updateTeacher() {
		// �����ݿ��ȡ������Ϣ
		course = cDao.getCourseById(course);
		teacher = tDao.getTeacherById(teacher);
		// ���ý�ʦ��Ϣ���������ݿ�
		course.setTeacher(teacher);
		cDao.update(course);
		return null;
	}

	// initҳ����½�����Ϣ
	public String updateRoom() {
		// �����ݿ��ȡ������Ϣ
		course = cDao.getCourseById(course);
		room = rDao.getRoomById(room);
		// ���ý�����Ϣ���������ݿ�
		course.setRoom(room);
		cDao.update(course);
		return null;
	}

	// chooseҳ���ж��Ƿ�Ϊ��ѡ��Ϣ
	public String checkSelect() {
		// �����ݿ��ȡstudent������Ϣ
		Student student = reloadStudent();
		// ȡ��student�Ŀγ���Ϣ
		Set<Course> courses = student.getCourses();
		// ��ʼ��һ���տγ�
		Course testCourse = null;
		boolean flag = false;
		// ����course
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			testCourse = iterator.next();
			// �жϣ���ҳ�������Ŀγ�id���Ƿ�����student��ϵ�Ŀγ�courses��,�������ж�
			if (testCourse.getId() == course.getId()) {
				flag = true;
				break;
			}
		}
		// ��ҳ��choose.jsp����ж���Ϣ
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
			System.out.println("�Ƿ�Ϊ��ѡ�γ̣�" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ����choose.jspҳ�洫���γ�ѡ����Ϣ�����ݿ���и���
	public String checkIt() {
		// �����ݿ��ȡstudent������Ϣ
		Student student = reloadStudent();
		// �����ݿ��ȡcourse������Ϣ
		course = cDao.getCourseById(course);
		// ȡ��student�Ŀγ���Ϣ
		Set<Course> courses = student.getCourses();
		// ��ʼ��һ���տγ�
		Course testCourse = null;
		boolean flag = false;
		// ����course
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			testCourse = iterator.next();
			// �жϣ���ҳ�������Ŀγ�id���Ƿ�����student��ϵ�Ŀγ�courses��,�������ж�
			if (testCourse.getId() == course.getId()) {
				flag = true;
				break;
			}
		}
		// �ж�flag��student���й����γ̲���������ɾ������������
		if (flag) {
			student.getCourses().remove(testCourse);
		} else {
			student.getCourses().add(course);
		}
		// �������ݿ�
		sDao.update(student);
		return null;
	}

	// Ϊchoose.jspҳ����ѡ�γ���Ϣ����׼����ѡ�γ���Ϣ
	public String getSelect() {
		Student student = reloadStudent();
		// ȡ��student�Ŀγ���Ϣ����session
		ActionContext.getContext().getSession().put("SELECTCOURSE", student.getCourses());
		// ����ҳ��
		return "choosemain";
	}

	private Student reloadStudent() {
		// ��session��ȡstudent��Ϣ
		Student student = (Student) ActionContext.getContext().getSession().get("LOGINSTUDENT");
		// �����ݿ����»�ȡ������Ϣ
//		student = sDao.get(student); // �����ӳټ���
		student = new StudentDAO().getStudentById(student);
		return student;
	}
}

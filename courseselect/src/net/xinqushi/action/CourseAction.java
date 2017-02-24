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
	// 获取请求信息
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

	// 新增或更新
	public String update() {
		return updateIt();
	}

	// 为插件提供修改方法
	public String modify() {
		return updateIt();
	}

	// 删除
	public String delete() {
		cDao.delete(course);
		return null;
	}

	private String updateIt() {
		// 判断添加?修改【添加时course.id为0；修改时>0】
		if (course.getId() == 0) {
			ActionContext.getContext().put("ADD", true);
		}
		// 向数据库存入数据
		cDao.update(course);
		return dataForCourses();
	}

	// 为页面准备分页数据
	public String dataForCourses() {
		// 引入request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 定义pager并初始化总页数
		Pager pager = new Pager();
		// 调用方法准备数据
		dataPrepare(pager);
		// 设置请求路径
		String url = "/course/dataForCourses";
		// 将导航条存入session
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		// 控制台打印str[查错用]
		// System.out.println(str.toString());
		return "coursemain";
	}

	private void dataPrepare(Pager pager) {
		// 设置初始页码【当网页未传递时】
		if (page == 0) {
			page = 1;
		}
		cDao.initPageCount(pager);
		// 对页面传递的页码参数进行判断
		if (page > pager.getPageCount()) {
			page = pager.getPageCount();
		}
		// 判断对course的操作是修改还是新增【新增时数据库记录数增加，page应该定位到新增页面】
		if (null != ActionContext.getContext().get("ADD")) {
			page = pager.getPageCount();
		}
		// 设置需要显示的页数
		pager.setPageNo(page);
		List<Course> list = cDao.getCourses(pager);
		// 判断
		if (list.size() > 0) {
			// 将分页教室信息存入session
			ActionContext.getContext().getSession().put("COURSES", list);
			// 将所有教室、教师信息存入session
			ActionContext.getContext().getSession().put("ROOMS", rDao.getRooms());
			ActionContext.getContext().getSession().put("TEACHERS", tDao.getTeachers());
		}
	}

	// 为修改插件提供数据
	public String getCourseById() {
		course = cDao.getCourseById(course);
		// 解决向jsp页面传送数据乱码
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// 向页面发送信息
		try {
			ServletActionContext.getResponse().getWriter()
					.print(course.getId() + "!" + course.getName() + "!" + course.getType() + "!" + course.getHours());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 为init.jsp页面准备显示数据【分页】
	public String initMain() {
		// 引入request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 定义pager并初始化总页数
		Pager pager = new Pager();
		dataPrepare(pager);
		// 设置请求路径
		String url = "/course/initMain";
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		return "initmain";
	}

	// 为choose.jsp页面准备显示数据【分页】
	public String chooseMain() {
		// 引入request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 定义pager并初始化总页数
		Pager pager = new Pager();
		dataPrepare(pager);
		// 设置请求路径
		String url = "/course/chooseMain";
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		return "choosemain";
	}

	// init页面更新教师信息
	public String updateTeacher() {
		// 从数据库获取完整信息
		course = cDao.getCourseById(course);
		teacher = tDao.getTeacherById(teacher);
		// 设置教师信息并更新数据库
		course.setTeacher(teacher);
		cDao.update(course);
		return null;
	}

	// init页面更新教室信息
	public String updateRoom() {
		// 从数据库获取完整信息
		course = cDao.getCourseById(course);
		room = rDao.getRoomById(room);
		// 设置教室信息并更新数据库
		course.setRoom(room);
		cDao.update(course);
		return null;
	}

	// choose页面判断是否为已选信息
	public String checkSelect() {
		// 从数据库获取student完整信息
		Student student = reloadStudent();
		// 取出student的课程信息
		Set<Course> courses = student.getCourses();
		// 初始化一个空课程
		Course testCourse = null;
		boolean flag = false;
		// 遍历course
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			testCourse = iterator.next();
			// 判断：网页传过来的课程id号是否属于student关系的课程courses中,包含则中断
			if (testCourse.getId() == course.getId()) {
				flag = true;
				break;
			}
		}
		// 向页面choose.jsp输出判断信息
		try {
			ServletActionContext.getResponse().getWriter().print(flag);
			System.out.println("是否为已选课程：" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 接收choose.jsp页面传来课程选择信息对数据库进行更改
	public String checkIt() {
		// 从数据库获取student完整信息
		Student student = reloadStudent();
		// 从数据库获取course完整信息
		course = cDao.getCourseById(course);
		// 取出student的课程信息
		Set<Course> courses = student.getCourses();
		// 初始化一个空课程
		Course testCourse = null;
		boolean flag = false;
		// 遍历course
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			testCourse = iterator.next();
			// 判断：网页传过来的课程id号是否属于student关系的课程courses中,包含则中断
			if (testCourse.getId() == course.getId()) {
				flag = true;
				break;
			}
		}
		// 判断flag对student进行关联课程操作：有则删除，无则新增
		if (flag) {
			student.getCourses().remove(testCourse);
		} else {
			student.getCourses().add(course);
		}
		// 更新数据库
		sDao.update(student);
		return null;
	}

	// 为choose.jsp页面已选课程信息窗口准备已选课程信息
	public String getSelect() {
		Student student = reloadStudent();
		// 取出student的课程信息存入session
		ActionContext.getContext().getSession().put("SELECTCOURSE", student.getCourses());
		// 返回页面
		return "choosemain";
	}

	private Student reloadStudent() {
		// 从session获取student信息
		Student student = (Student) ActionContext.getContext().getSession().get("LOGINSTUDENT");
		// 从数据库重新获取完整信息
//		student = sDao.get(student); // 测试延迟加载
		student = new StudentDAO().getStudentById(student);
		return student;
	}
}

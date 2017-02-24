package net.xinqushi.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.common.PictureUtil;
import net.xinqushi.dao.StudentDAO;
import net.xinqushi.pojo.Student;

public class StudentAction extends ActionSupport {
	private Student student;
	private StudentDAO sDao = new StudentDAO();
	// 上传图片需要引入的变量
	private File image;
	private String imageContentType;
	private String imageFileName;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	//
	public String add() {
		// 调用dao方法进行保存
		sDao.update(student);
		// 将注册后的信息存入main页面
		Student loginStudent = sDao.get(student);
		ActionContext.getContext().getSession().put("LOGINSTUDENT", loginStudent);
		// 返回页面
		return "main";
	}

	//
	public String check() {
		// 获取student信息进行判断
		Student checkStudent = sDao.check(student);
		// 判断:为空时返回登录页
		if (null == checkStudent) {
			// 返回信息
			ActionContext.getContext().getSession().put("INFO", "用户名或密码错误！");
			return "login";
		}
		// 不为空时将信息存入session
		ActionContext.getContext().getSession().put("LOGINSTUDENT", checkStudent);
		return "main";
	}

	// 密码检测
	public String checkPwd() {
		String result = "0";
		// 调用dao方法进行判定
		if (sDao.checkPwd(student)) {
			result = "1";
		}
		// 返回结果信息
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 注销
	public String logout() {
		// 将session中的登陆信息置空
		ActionContext.getContext().getSession().put("LOGINSTUDENT", null);
		return "main";
	}

	// 更新
	public String update() {
		return updateIt();
	}

	//
	public String modify() {
		return updateIt();
	}

	private String updateIt() {
		// 获取页面修改的student信息
		String newPhone = student.getPhone();
		String newGrade = student.getGrade();
		String newPwd = student.getPwd();
		String newSex = student.getSex();
		// 从session中获取student信息
		student = (Student) ServletActionContext.getRequest().getSession().getAttribute("LOGINSTUDENT");
		// 更新student信息
		student.setPhone(newPhone);
		student.setPwd(newPwd);
		student.setGrade(newGrade);
		student.setSex(newSex);
		// 判断图片信息
		if (null != image) {
			String url = PictureUtil.makeDir(imageFileName, image);

			// 获取之前的照片路径，不为空时删除
			String oldUrl = sDao.getUrl(student);
			System.out.println("旧照地址：" + oldUrl); // 测试用
			if (null != oldUrl) {
				String oldFile = ServletActionContext.getRequest().getServletContext().getRealPath("") + oldUrl;
				File myFile = new File(oldFile);
				if (myFile.exists()) {
					myFile.delete();
				}
			}
			// 设置新的照片地址信息
			student.setPhoto(url);
		}
		// 向数据库更新设置了电话和照片信息的学生信息
		sDao.update(student);
		// 向session中更新学生信息【更完整？】
		ActionContext.getContext().getSession().put("LOGINSTUDENT", student);
		// 返回页面
		return "main";
	}
}

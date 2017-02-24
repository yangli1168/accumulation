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
	// �ϴ�ͼƬ��Ҫ����ı���
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
		// ����dao�������б���
		sDao.update(student);
		// ��ע������Ϣ����mainҳ��
		Student loginStudent = sDao.get(student);
		ActionContext.getContext().getSession().put("LOGINSTUDENT", loginStudent);
		// ����ҳ��
		return "main";
	}

	//
	public String check() {
		// ��ȡstudent��Ϣ�����ж�
		Student checkStudent = sDao.check(student);
		// �ж�:Ϊ��ʱ���ص�¼ҳ
		if (null == checkStudent) {
			// ������Ϣ
			ActionContext.getContext().getSession().put("INFO", "�û������������");
			return "login";
		}
		// ��Ϊ��ʱ����Ϣ����session
		ActionContext.getContext().getSession().put("LOGINSTUDENT", checkStudent);
		return "main";
	}

	// ������
	public String checkPwd() {
		String result = "0";
		// ����dao���������ж�
		if (sDao.checkPwd(student)) {
			result = "1";
		}
		// ���ؽ����Ϣ
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ע��
	public String logout() {
		// ��session�еĵ�½��Ϣ�ÿ�
		ActionContext.getContext().getSession().put("LOGINSTUDENT", null);
		return "main";
	}

	// ����
	public String update() {
		return updateIt();
	}

	//
	public String modify() {
		return updateIt();
	}

	private String updateIt() {
		// ��ȡҳ���޸ĵ�student��Ϣ
		String newPhone = student.getPhone();
		String newGrade = student.getGrade();
		String newPwd = student.getPwd();
		String newSex = student.getSex();
		// ��session�л�ȡstudent��Ϣ
		student = (Student) ServletActionContext.getRequest().getSession().getAttribute("LOGINSTUDENT");
		// ����student��Ϣ
		student.setPhone(newPhone);
		student.setPwd(newPwd);
		student.setGrade(newGrade);
		student.setSex(newSex);
		// �ж�ͼƬ��Ϣ
		if (null != image) {
			String url = PictureUtil.makeDir(imageFileName, image);

			// ��ȡ֮ǰ����Ƭ·������Ϊ��ʱɾ��
			String oldUrl = sDao.getUrl(student);
			System.out.println("���յ�ַ��" + oldUrl); // ������
			if (null != oldUrl) {
				String oldFile = ServletActionContext.getRequest().getServletContext().getRealPath("") + oldUrl;
				File myFile = new File(oldFile);
				if (myFile.exists()) {
					myFile.delete();
				}
			}
			// �����µ���Ƭ��ַ��Ϣ
			student.setPhoto(url);
		}
		// �����ݿ���������˵绰����Ƭ��Ϣ��ѧ����Ϣ
		sDao.update(student);
		// ��session�и���ѧ����Ϣ������������
		ActionContext.getContext().getSession().put("LOGINSTUDENT", student);
		// ����ҳ��
		return "main";
	}
}

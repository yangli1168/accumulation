package net.xinqushi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.common.BtnBuilder;
import net.xinqushi.common.Pager;
import net.xinqushi.dao.TeacherDAO;
import net.xinqushi.pojo.Teacher;

public class TeacherAction extends ActionSupport {
	private TeacherDAO tDao = new TeacherDAO();
	// ��ȡ������Ϣ
	private Teacher teacher;
	private int page;

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		tDao.delete(teacher);
		return null;
	}

	private String updateIt() {
		// �ж����?�޸ġ����ʱteacher.idΪ0���޸�ʱ>0��
		if (teacher.getId() == 0) {
			ActionContext.getContext().put("ADD", true);
		}
		// �����ݿ��������
		tDao.update(teacher);
		return dataForTeachers();
	}

	// Ϊҳ��׼����ҳ����
	public String dataForTeachers() {
		// ����request
		HttpServletRequest request = ServletActionContext.getRequest();
		// ���ó�ʼҳ�롾����ҳδ����ʱ��
		if (page == 0) {
			page = 1;
		}
		// ����pager����ʼ����ҳ��
		Pager pager = new Pager();
		tDao.initPageCount(pager);
		// ��ҳ�洫�ݵ�ҳ����������ж�
		if (page > pager.getPageCount()) {
			page = pager.getPageCount();
		}
		// �ж϶�teacher�Ĳ������޸Ļ�������������ʱ���ݿ��¼�����ӣ�pageӦ�ö�λ������ҳ�桿
		if (null != ActionContext.getContext().get("ADD")) {
			page = pager.getPageCount();
		}
		// ������Ҫ��ʾ��ҳ��
		pager.setPageNo(page);
		List<Teacher> list = tDao.getTeachers(pager);
		// �ж�
		if (list.size() > 0) {
			// �����н�����Ϣ����session
			ActionContext.getContext().getSession().put("TEACHERS", list);
		}
		//��������·��
		String url = "/teacher/dataForTeachers";
		// ������������session
		ActionContext.getContext().getSession().put("BTNS", BtnBuilder.buildBtn(request, pager, page, url));
		// ����̨��ӡstr[�����]
//		System.out.println(str.toString());
		return "teachermain";
	}

	// Ϊ�޸Ĳ���ṩ����
	public String getTeacherById() {
		teacher = tDao.getTeacherById(teacher);
		// �����jspҳ�洫����������
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		// ��ҳ�淢����Ϣ
		try {
			ServletActionContext.getResponse().getWriter()
					.print(teacher.getId() + "!" + teacher.getName() + "!" + teacher.getSex() + "!" + teacher.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

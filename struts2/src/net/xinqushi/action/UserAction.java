package net.xinqushi.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.xinqushi.dao.PictureDAO;
import net.xinqushi.dao.UserDAO;
import net.xinqushi.pojo.Picture;
import net.xinqushi.pojo.User;

public class UserAction extends ActionSupport {
	// ��ȡ�����͹�����user��Ϣ
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// ����request,response,session
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	//
	PrintWriter out;

	// ��
	public String add() throws Exception {

		new UserDAO().addUser(user);
		return "list";
	}

	// ɾ
	public String delete() throws Exception {
		new UserDAO().deleteUser(user);
		return null;
	}

	// ��
	public String modify() throws Exception {

		// Ϊmodifyҳ���ṩuser��Ϣ
		user = new UserDAO().getUserById(user.getId());
		// Ϊmodifyҳ���ṩpicture��Ϣ-16.9.25
		PictureDAO pDao = new PictureDAO();
		ArrayList<Picture> pictures = pDao.getPictures(user.getId(), "user");
		ActionContext ctx = ActionContext.getContext();
		ctx.put("PICTURESLIST", pictures);
		return "modify";
	}

	// �޸�->����
	public String save() throws Exception {
		new UserDAO().updateUser(user);
		return "list";
	}

	/** �ظ���֤ */
	public String checkExists() throws Exception {

		out = response.getWriter();
		if (new UserDAO().checkExists(user)) {
			out.print("1");
		} else {
			out.print("0");
		}
		return null;
	}

	/** ��½��֤ */
	public String checkLogin() throws Exception {

		out = response.getWriter();
		if (new UserDAO().checkLogin(user)) {
			// �����¼�û���Ϣ
			session.setAttribute("USER", user);
			out.print("1");
		} else {
			out.print("0");
		}
		return null;
	}

	/** ����ת��ҳ�淽�� */
	public String list() throws Exception {

		ArrayList<User> list = new UserDAO().getUsers();
		ActionContext ctx = ActionContext.getContext();
		ctx.put("USERLIST", list);
		return SUCCESS;
	}

}

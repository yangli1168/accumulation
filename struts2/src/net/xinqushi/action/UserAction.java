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
	// 获取请求发送过来的user信息
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 引入request,response,session
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	//
	PrintWriter out;

	// 增
	public String add() throws Exception {

		new UserDAO().addUser(user);
		return "list";
	}

	// 删
	public String delete() throws Exception {
		new UserDAO().deleteUser(user);
		return null;
	}

	// 改
	public String modify() throws Exception {

		// 为modify页面提供user信息
		user = new UserDAO().getUserById(user.getId());
		// 为modify页面提供picture信息-16.9.25
		PictureDAO pDao = new PictureDAO();
		ArrayList<Picture> pictures = pDao.getPictures(user.getId(), "user");
		ActionContext ctx = ActionContext.getContext();
		ctx.put("PICTURESLIST", pictures);
		return "modify";
	}

	// 修改->保存
	public String save() throws Exception {
		new UserDAO().updateUser(user);
		return "list";
	}

	/** 重复验证 */
	public String checkExists() throws Exception {

		out = response.getWriter();
		if (new UserDAO().checkExists(user)) {
			out.print("1");
		} else {
			out.print("0");
		}
		return null;
	}

	/** 登陆验证 */
	public String checkLogin() throws Exception {

		out = response.getWriter();
		if (new UserDAO().checkLogin(user)) {
			// 保存登录用户信息
			session.setAttribute("USER", user);
			out.print("1");
		} else {
			out.print("0");
		}
		return null;
	}

	/** 常用转向页面方法 */
	public String list() throws Exception {

		ArrayList<User> list = new UserDAO().getUsers();
		ActionContext ctx = ActionContext.getContext();
		ctx.put("USERLIST", list);
		return SUCCESS;
	}

}

package net.xinqushi.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import net.xinqushi.pojo.User;
import net.xinqushi.service.UserService;

public class UserAction {
	// 接受网页的请求信息
	private User user;
	// 注入service
	@Autowired
	private UserService userService;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 保存
	public String add() {
		userService.add(user);
		return "main";
	}

	// 判断用户是否存在
	public String checkExists() {
		boolean flag = userService.checkExists(user);
		// 判断并输出:真为1
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			if (flag) {
				out.print("1");
			} else {
				out.print("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 登录
	public String login() {
		String result = "login";
		// 判断：存在则返回主页，否则返回登录页
		if (userService.checkExists(user)) {
			ActionContext.getContext().getSession().put("LOGINUSER", user);
			result = "main";
		}
		return result;
	}

	// 注销用户
	public String logout() {
		ActionContext.getContext().getSession().put("LOGINUSER", null);
		return "main";
	}
}

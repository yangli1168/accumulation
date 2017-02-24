package net.xinqushi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.xinqushi.dao.UserDAO;
import net.xinqushi.pojo.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	//注入dao
	@Autowired
	private UserDAO userDAO;
//	@Resource
//	public void setUserDAO(UserDAO userDAO) {
//		this.userDAO = userDAO;
//	}

	@RequestMapping("/check.do")
//	@RequestMapping(value = "/check")
	public String check(HttpSession session,User user){
		String path = "login";
		//判断
		if (userDAO.isValid(user)) {
			System.out.println("数据库有此用户！");
			session.setAttribute("NAME", user.getName());
			path="welcome";
		}else {
			System.out.println("数据库无此用户！");
		}
		//返回
		return path;
	}
	
	@RequestMapping(value = "/login.do")
//	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
}

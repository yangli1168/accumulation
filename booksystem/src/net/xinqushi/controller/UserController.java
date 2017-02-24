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
	//ע��dao
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
		//�ж�
		if (userDAO.isValid(user)) {
			System.out.println("���ݿ��д��û���");
			session.setAttribute("NAME", user.getName());
			path="welcome";
		}else {
			System.out.println("���ݿ��޴��û���");
		}
		//����
		return path;
	}
	
	@RequestMapping(value = "/login.do")
//	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
}

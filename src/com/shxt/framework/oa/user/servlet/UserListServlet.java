package com.shxt.framework.oa.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shxt.framework.oa.user.model.User;
import com.shxt.framework.oa.user.service.UserService;

@WebServlet("/sys/user/list")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> userList = this.userService.list();
		//保存数据
		request.setAttribute("userList", userList);
		
		//请求转发传递数据
		request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
	
	}

}

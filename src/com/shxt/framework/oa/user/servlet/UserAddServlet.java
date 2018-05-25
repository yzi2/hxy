package com.shxt.framework.oa.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shxt.framework.common.exception.ServiceException;
import com.shxt.framework.oa.user.model.User;
import com.shxt.framework.oa.user.service.UserService;

/**
 * Servlet implementation class UserAddServlet
 */
@WebServlet("/sys/user/add")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//获取客户端的数据
		String account = request.getParameter("account");
		String sex = request.getParameter("sex");
		String user_name = request.getParameter("user_name");
		
		//封装客户端数据
		User user = new User();
		user.setAccount(account);
		user.setUser_name(user_name);
		user.setSex(sex);
		try {
			this.userService.save(user);
			//->成功
			response.sendRedirect(request.getContextPath()+"/sys/user/list");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(request, response);
		}
	
	}

}

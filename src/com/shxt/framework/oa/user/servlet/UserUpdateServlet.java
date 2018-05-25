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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/sys/user/update")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		
		User user = this.userService.load(Integer.parseInt(user_id));
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/WEB-INF/jsp/user/update.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//获取客户端的数据
		String account = request.getParameter("account");
		String sex = request.getParameter("sex");
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		
		//封装客户端数据
		User user = new User();
		user.setAccount(account);
		user.setUser_name(user_name);
		user.setSex(sex);
		user.setUser_id(Integer.parseInt(user_id));
		try {
			this.userService.update(user);
			//->成功
			response.sendRedirect(request.getContextPath()+"/sys/user/list");
			
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(request, response);
		}
	
	}

}

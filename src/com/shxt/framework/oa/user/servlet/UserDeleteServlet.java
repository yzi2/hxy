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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/sys/user/delete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取客户端的数据
		String user_id = request.getParameter("user_id");

		try {
			this.userService.remove(Integer.parseInt(user_id));
			// ->成功
			response.sendRedirect(request.getContextPath() + "/sys/user/list");
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}

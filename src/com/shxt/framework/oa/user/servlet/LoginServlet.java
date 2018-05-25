package com.shxt.framework.oa.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shxt.framework.oa.user.model.User;
import com.shxt.framework.oa.user.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/sys/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决POST请求的中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//获取客户端的数据
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		try {
			//调用业务逻辑层方法
			User user = this.userService.login(account, password);
			//将用户信息保存到Session范围
			HttpSession session = request.getSession();
			session.setAttribute("session_user", user);
			//重定向到主界面Servlet--标准写法
			response.sendRedirect(request.getContextPath()+"/sys/main");
		} catch (Exception e) {
			e.printStackTrace();//控制台显示详细信息
			//传递数据
			request.setAttribute("account", account);//回显数据
			request.setAttribute("message", e.getMessage());//提示错误信息
			//请求转发传递数据
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath()+"/index");
		
	}
	

}

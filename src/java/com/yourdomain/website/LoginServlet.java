package com.yourdomain.website;

import com.yourdomain.website.config.AppConfig;
import com.yourdomain.website.dao.UserDAO;
import com.yourdomain.website.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * 完成网站登录的Servlet
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {
		request.setAttribute("appId", AppConfig.APP_ID);
		request.setAttribute("redirectUri", URLEncoder.encode("http://www.yourdomain.com:8080/" + AppConfig.APP_NAME + "/rr_login", "UTF-8"));
		RequestDispatcher welcomeDispatcher = request.getRequestDispatcher("/views/login.jsp");
		welcomeDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username != null && password != null) {
			User user = UserDAO.getInstance().getUser(username);
			if (user != null) {
				if (password.equals(user.getPassword())) {
					//用户名和密码验证通过，将用户身份信息保存在会话中
					request.getSession().setAttribute("user", user);
					//已登录，跳转到个人主页
                    System.out.println("/" + AppConfig.APP_NAME + "/profile");
					response.sendRedirect("/" + AppConfig.APP_NAME + "/profile");
					return;
				}
			}
		}
		//没登录成功，跳转到登录页
        System.out.println("/" + AppConfig.APP_NAME + "/login");
		response.sendRedirect("/" + AppConfig.APP_NAME + "/login");
	}
}

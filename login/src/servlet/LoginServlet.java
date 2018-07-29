package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.LoginService;
import service.impl.LoginImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginImpl();
	private String url;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		User returnUser = loginService.loginUser(user);
		if (returnUser != null) {
			url = "/Index.jsp";
			request.getSession().setAttribute("returnUser", returnUser);
		} else {
			url = "/Login.jsp";
			request.setAttribute("Context", "管理员账号或者密码错误");
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

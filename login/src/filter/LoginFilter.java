package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private String uri;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String uri = request.getRequestURI();
		String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		if ("Login.jsp".equals(requestPath) || "LoginServlet".equals(requestPath)) {
			arg2.doFilter(request, response);
		} else {
			HttpSession httpSession = request.getSession(false);
			if (httpSession != null) {
				Object object = httpSession.getAttribute("returnUser");
				if (object != null) {
					if ("ListServlet".equals(requestPath)) {
						requestPath = "/List.jsp";
					}
					arg2.doFilter(request, response);
				} else {
					requestPath = "/Login.jsp";
				}
			} else {
				requestPath = "/Login.jsp";
			}
			request.getRequestDispatcher(requestPath).forward(request, response);
		}

	}

}

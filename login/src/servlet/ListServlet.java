package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Person;
import service.ListService;
import service.impl.ListImpl;
import utils.PersonBean;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ListService listService = new ListImpl();
	private String url;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 第一次访问的时候没有数据
			String current = request.getParameter("currentPage");
			if (current == null || "".equals(current)) {
				current = "1";
			}
			// 类型转换
			Integer integer = Integer.valueOf(current);

			PersonBean<Person> personBean = new PersonBean<Person>();
			// 设置当前页
			personBean.setCurrentPage(integer);
			// 得到数据
			listService.findAll(personBean);
			// 传递数据
			request.setAttribute("personBean", personBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

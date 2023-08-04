package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import dao.UserDao;

/**
 * <p>
 *
 * </p>
 *
 
 */
/**
 * Servlet implementation class FindByName
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if (method.equals("findall")) {
			findall(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if (method.equals("findall")) {
			findall(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} 
	}

	// 增加
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String identifier = request.getParameter("identifier");
		String versions = request.getParameter("versions");
		String count = request.getParameter("count");
		User bean = new User();
		bean.setId(Integer.parseInt(id));

	

		bean.setIdentifier(identifier);

		bean.setVersions(versions);

		bean.setCount(Integer.valueOf(count));
		boolean b = UserDao.add(bean);
		if (b) {
			request.setAttribute("msg", "add sucess");
		} else {
			request.setAttribute("msg", "addfail");
		}
		request.getRequestDispatcher("/msg.jsp").forward(request, response);

	}

	private void findall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> list = UserDao.getAll();
		request.setAttribute("LIST", list);
		request.getRequestDispatcher("/UserList.jsp").forward(request, response);
	}

}

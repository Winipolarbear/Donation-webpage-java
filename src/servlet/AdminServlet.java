package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entity.Money;
import entity.User;
import dao.MoneyDao;
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
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/msexcel;charset=UTF-8");
		try {
			response.addHeader("Content-Disposition", "attachment;filename=file.xls");
			OutputStream out = response.getOutputStream();

			List<Money> list=MoneyDao.getAll();
			if(list!=null){
				StringBuffer sb=new StringBuffer();
				sb.append("identifier   ");
				sb.append("\t");
				sb.append("money   ");
				sb.append("\t");
				sb.append("visits   ");
				
				
				sb.append("\r\n");
				
				for(Money p:list){
					sb.append(p.getIdentifier()   );
					sb.append("\t");
					sb.append(p.getMoney() );
					sb.append("\t");
					sb.append(p.getCount()   );
					
					sb.append("\r\n");	
				}
				
				String content=sb.toString();
				System.out.println("sb"+content);
				out.write(content.getBytes());
				out.flush();
				out.close();
			}else{
				throw new Exception("111100");
			}
			out.flush();
			out.close();
	} catch (Exception e) {
		e.printStackTrace();
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

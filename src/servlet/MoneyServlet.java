package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import entity.Money;
import entity.User;
import util.ServletUtil;
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
@WebServlet("/MoneyServlet")
public class MoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoneyServlet() {
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






	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String money = request.getParameter("money");
		
		
		
		String identifier="";
		 Cookie[] cookies = request.getCookies();
		//see if its first time visited
		 if(cookies!=null){//if no take previous value
			for(int i=0;cookies!=null && i<cookies.length;i++) {//if cookie exist
				if(cookies[i].getName().equals("uuid")) {//get userid from cookie
					
					identifier=cookies[i].getValue();
					
					
					
				}
				
			}
		 }
		Money bean = new Money();
		bean.setIdentifier(identifier);

		bean.setMoney(BigDecimal.valueOf(Float.valueOf(money)));

		bean.setCreatTime(new Date());
		boolean b = MoneyDao.add(bean);
		if (b) {
			request.setAttribute("msg", "add scuess");
		} else {
			request.setAttribute("msg", "add fail");
		}
		ServletUtil.ok(response, "msg.jsp?money="+money);

	}


	private void findall(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Money> list = MoneyDao.getAll();
		request.setAttribute("LIST", list);
		request.getRequestDispatcher("/MoneyList.jsp").forward(request, response);
	}

}

package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

@WebServlet("/ToIndexServlet")
public class ToIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Cookie[] cookies = request.getCookies();
		//see if first time visited
		 if(cookies!=null){//if not take previus value
			 boolean flag=true;
			for(int i=0;cookies!=null && i<cookies.length;i++) {//if cookie exist
				
				if(cookies[i].getName().equals("uuid")) {//get userid from cookies
					flag=false;
					String uuid=cookies[i].getValue();
					User user=UserDao.getByIdentifier(uuid);
					request.getSession().setAttribute("versions", user.getVersions());
					user.setCount(user.getCount()+1);
					UserDao.updateById(user);
					
					
				}
				
			}
			
			if(flag){
				
				String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
				 User user=new User();
				 user.setCount(1);
				 user.setIdentifier(uuid);
				 String versions=getRondan();
				 user.setVersions(versions);
				//set cookie
					Cookie cookie = new Cookie("uuid", uuid);
					
					cookie.setMaxAge(7*24*60*60);
					response.addCookie(cookie);
				 //save data to data base
				 UserDao.add(user);
				 request.getSession().setAttribute("versions", user.getVersions());
				 
			}
		 }else{//if this is the first time cookie is made, it is saved to database
			 String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			 User user=new User();
			 user.setCount(1);
			 user.setIdentifier(uuid);
			 String versions=getRondan();
			 user.setVersions(versions);
			//set cookie
				Cookie cookie = new Cookie("uuid", uuid);
				
				cookie.setMaxAge(7*24*60*60);
				response.addCookie(cookie);
			 //save to database
			 UserDao.add(user);
			 request.getSession().setAttribute("versions", user.getVersions());
		 }
		 
		 response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	//get random number from 1 to 10，if odd number then say: Make a donation!. Otherwise, Please make a donation!
	public String getRondan(){
		String str="";
		int num=(int)(Math.random()*10+1);
		if(num%2!=0){
			str="Please make a donation!";
			
		}else{
			str="Make a donation! ";
		}
		return str;
	}
}

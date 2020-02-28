package cn.wids.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wids.dao.userDAO;
import cn.wids.dto.userDTO;

public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String value=request.getParameter("aaa");
		System.out.println(value);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String pass = request.getParameter("userPass");
		System.out.println(name+"\t"+pass);
		/*查询数据库是否存在该用户，并验证用户信息*/
		userDAO dao=new userDAO();
		userDTO dto=dao.getuserinfo(name, pass);
		if (dto==null) {
			request.getRequestDispatcher("denglu.html").forward(request, response);
			
		}else {
			request.getRequestDispatcher("main.html").forward(request, response);
		}
		
	}
	
}

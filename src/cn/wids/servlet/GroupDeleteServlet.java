package cn.wids.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.wids.ai.*;


@WebServlet("/GroupDeleteServlet")
public class GroupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptName = request.getParameter("deptName");
		String temp = GroupDelete.groupDelete(deptName);
			PrintWriter pw = response.getWriter();
			pw.write(temp);
			pw.flush();
			pw.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

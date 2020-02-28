package cn.wids.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wids.ai.GroupGetusers;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("groupId");
		String result =GroupGetusers.getUsers(id);
		JSONObject obj = JSONObject.parseObject(result);
		
		String temp = obj.getString("result");
		JSONObject obj02 = JSONObject.parseObject(temp);
		String data = obj02.getString("user_id_list");
		PrintWriter pw = response.getWriter();
		pw.write(data);
		pw.flush();
		pw.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

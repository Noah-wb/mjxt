package cn.wids.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wids.ai.FaceSearch;

@WebServlet("/FacesearchServlet")
public class FacesearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String image = request.getParameter("image");
		String groupid = request.getParameter("groupId");
		String result = FaceSearch.search(image, groupid);
		JSONObject obj = JSONObject.parseObject(result);
		String r = obj.getString("result");
		JSONObject obj02 = JSONObject.parseObject(r);
		String data = obj02.getString("user_list");
		 
		PrintWriter pw = response.getWriter();
		pw.write(data);
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

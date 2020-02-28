package cn.wids.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wids.ai.FaceGetList;


@WebServlet("/FacegetlistServlet")
public class FacegetlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupId = request.getParameter("groupId");
		String userId = request.getParameter("userId");
		String result = FaceGetList.getList(userId, groupId);
		JSONObject obj = JSONObject.parseObject(result);
		 String r = obj.getString("result");
		JSONObject obj02 = JSONObject.parseObject(r);
		String data = obj02.getString("face_list");
		 
		PrintWriter pw = response.getWriter();
		pw.write(data);
		pw.flush();
		pw.close();
		System.out.println("进入方法");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}


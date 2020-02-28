package cn.wids.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wids.ai.GroupGetlist;


@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受客户端的请求，处理请求（通过IA接口查询所有的组信息）
		
		String result = GroupGetlist.GroupGetlist();
		JSONObject obj = JSONObject.parseObject(result);
		String reStr = obj.getString("result");
		JSONObject resobj = JSONObject.parseObject(reStr);
		String data = resobj.getString("group_id_list");
		PrintWriter pw = response.getWriter();
		pw.write(data);
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
package cn.wids.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.wids.dao.EmpDeviceDAO;
import cn.wids.dto.EmpDevDTO;

@WebServlet("/EmpDevServlet")
public class EmpDevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		EmpDeviceDAO dao = new EmpDeviceDAO();
		response.setCharacterEncoding("UTF-8");
		if("add".equals(method)){
			//要执行新增操作
			
			String devNum = request.getParameter("devNum");
			String empNum = request.getParameter("empNum");
			boolean result = dao.insertDeviceInfo(empNum,devNum);
			int code = -1;
			String msg = "新增数据失败！";
			if(result){
				code = 0;
				msg = "新增数据成功！";
			}
			String data = "{\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
			PrintWriter pw = response.getWriter();
			pw.write(data);
			pw.flush();
			pw.close();
		}else if("delete".equals(method)){
			//执行删除操作
			int code = -1;
			String msg = "删除数据失败";
			String devNum = request.getParameter("devNum");
			String empNum = request.getParameter("empNum");
			boolean result = dao.deleteDeviceInfo(empNum, devNum);
			if(result){
				code = 0;
				msg = "删除数据成功";
			}
			String data01 = "{\"code\":" + code + ",\"msg\":" + msg + "}";
			System.out.println(data01);
			String data = "{\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
			System.out.println(data);
			PrintWriter pw = response.getWriter();
			pw.write(data);
			pw.flush();
			pw.close();
		}else if("list".equals(method)){
			//执行查询列表信息的操作
			List<EmpDevDTO> list = dao.getEmpDevList();
			String str = JSONObject.toJSONString(list);
			PrintWriter pw = response.getWriter();
			pw.write(str);
			pw.flush();
			pw.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

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

import cn.wids.dao.*;
import cn.wids.dto.*;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if("add".equals(method)){
			
			String empnum =  request.getParameter("empNum");
			String empname =  request.getParameter("empName");
			String empsex =  request.getParameter("empSex");
			String empage =  request.getParameter("empAge");
			int empage2=Integer.parseInt(empage);
			String empdept =  request.getParameter("empDept");
			empDTO emp=new empDTO(empnum, empname, empsex, empage2, empdept);
			empDAO edao=new empDAO();
			boolean b = edao.insertEmpInfo(emp);
			String page = b?"denglu.html":"login.html";
			String tips = b?"注册成功！":"注册失败！";
			request.setAttribute("tips", tips);
			request.getRequestDispatcher(page).forward(request, response);
			
		}
		else if("update".equals(method)){
			String empnum =  request.getParameter("empNum");
			String empname =  request.getParameter("empName");
//			String empsex =  request.getParameter("empSex");
//			String empage =  request.getParameter("empAge");
//			int empage2=Integer.parseInt(empage);
//			String empdept =  request.getParameter("empDept");
			empDTO emp=new empDTO();
			empDAO edao=new empDAO();
			edao.updateemp(emp);
			
			
		}else if("delete".equals(method)){
			String empnum =  request.getParameter("empNum");
			empDAO edao=new empDAO();
			int code = -1;
			String msg = "删除失败";
			boolean result = edao.getempdelete(empnum);
			if (result) {
				code = 0;
				msg ="删除成功！";
			}
			String data = "{\"code\": " + code + ",\"msg\":\"" + msg + "\"}";
			PrintWriter pw = response.getWriter();
			pw.write(data);
			pw.flush();
			pw.close();
		}else if("list".equals(method)){
			//查询
			empDAO edao=new empDAO();
			List<empDTO> list = edao.getuserlist();
			String str = JSONObject.toJSONString(list);
			PrintWriter pw = response.getWriter();
			pw.write(str);
			pw.flush();
			pw.close();
		}else if("denglu".equals(method)){
			String empnum = request.getParameter("empNum");
			String empname = request.getParameter("empName");
			empDAO dao = new empDAO();
			empDTO dto = dao.getuserinfo(empnum, empname);
			if(dto == null){
			
				request.getRequestDispatcher("denglu.html").forward(request, response);
			}else{
				if(empnum.equals(dto.getEmpNum())){
					//密码正确，登录成功，跳转到主界面（如果用户登录成功，请将当前用户信息保存到session中）
					request.getSession().setAttribute("user", dto);
					response.sendRedirect("main.html");
				}else{
					//密码错误
					
					request.getRequestDispatcher("denglu.html").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

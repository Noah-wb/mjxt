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

import cn.wids.dao.DeviceDAO;
import cn.wids.dto.DeviceDTO;

@WebServlet("/DeviceServlet")
public class DeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		DeviceDAO dao = new DeviceDAO();
		response.setCharacterEncoding("UTF-8");
		if("add".equals(method)){
			//Ҫִ����������
			request.getParameter("");
			
		}else if("update".equals(method)){
			//ִ���޸Ĳ���
			
		}else if("delete".equals(method)){
			//ִ��ɾ������
			int code = -1;
			String msg = "ɾ������ʧ��";
			String devNum = request.getParameter("devNum");
			boolean result = dao.deleteDeviceInfo(devNum);
			if(result){
				code = 0;
				msg = "ɾ�����ݳɹ�";
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
			//ִ�в�ѯ�б���Ϣ�Ĳ���
			List<DeviceDTO> list = dao.getDeviceList();
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

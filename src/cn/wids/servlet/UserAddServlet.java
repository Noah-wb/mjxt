package cn.wids.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.net.SyslogAppender;

import cn.wids.ai.FaceAdd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 try {
			 DiskFileItemFactory factory = new DiskFileItemFactory(); 
			 // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中 
			 factory.setSizeThreshold(10*1024*1024); 
			 // 设置临时存储目录 
			 factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); 

			 ServletFileUpload upload = new ServletFileUpload(factory); 
			// 设置最大文件上传值 
			 upload.setFileSizeMax(10*1024*1024); 
			 // 设置最大请求值 (包含文件和表单数据) 
			 upload.setSizeMax(10*1024*1024);
			// 中文处理 
			 upload.setHeaderEncoding("UTF-8");
			//获取upload文件夹具体的磁盘路径
			String basePath =  request.getServletContext().getRealPath("upload");
			long time = System.currentTimeMillis();
			String path = basePath + "/" +time;
			
			List<FileItem> list = upload.parseRequest(request); 
			Map<String , String > map = new HashMap<>(); 
			for(FileItem item:list){
				 if (item.isFormField()) {
					 String name = item.getFieldName();//获取form表单字段名称
					String val = item.getString();//获取form表单字段值
					System.out.println(name+"\t"+val);
					map.put(name, val);
				}else {
					String name = item.getName();
					int index = name.lastIndexOf(".");
					String suf = name.substring(index);
					String fullPath = path  +suf;
					System.err.println(fullPath);
					item.write(new File(fullPath));
					map.put("path", fullPath);
				}
			 }
		String result =  FaceAdd.add(map.get("path"),map.get("groupId") , map.get("userId"));
		//服务器的内部跳转
		request.getRequestDispatcher("face_list.html").forward(request, response);
		
		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

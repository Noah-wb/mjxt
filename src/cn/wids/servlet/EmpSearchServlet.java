package cn.wids.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.util.Base64Util;
import cn.wids.ai.FaceSearch;
import cn.wids.ai.GroupGetlist;
import cn.wids.dao.EmpDeviceDAO;
import cn.wids.utils.FileUtil;

@WebServlet("/EmpSearchServlet")
public class EmpSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpSearchServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
			factory.setSizeThreshold(10 * 1024 * 1024);
			// 设置临时存储目录
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大文件上传值
			upload.setFileSizeMax(10 * 1024 * 1024);
			// 设置最大请求值 (包含文件和表单数据)
			upload.setSizeMax(20 * 1024 * 1024);
			// 中文处理
			upload.setHeaderEncoding("UTF-8");
			// 获取uoload文件夹具体路径
			String basePath = request.getServletContext().getRealPath("upload");
			long time = System.currentTimeMillis();
			String path = basePath + "/" + time;
			List<FileItem> list = upload.parseRequest(request);
			String imgPath = "";
			for (FileItem item : list) {
				if (item.isFormField()) {
				} else {
					String name = item.getName();
					int index = name.lastIndexOf(".");
					String suf = name.substring(index);
					String fullPath = path + suf;
					System.out.println(fullPath);
					try {

						item.write(new File(fullPath));
					} catch (Exception e) {

						e.printStackTrace();
					}
					imgPath = fullPath;
					System.out.println(name);
				}
			}
			// 查询到所有组
			String result = GroupGetlist.GroupGetlist();

			JSONObject obj = JSONObject.parseObject(result);
			String reStr = obj.getString("result");
			JSONObject resObj = JSONObject.parseObject(reStr);
			String data = resObj.getString("group_id_list");
			System.out.println(data);
			String groups = data.substring(1, data.length() - 1);
			groups = groups.replace("\"", "");
			// 准备上传的图片
			byte[] bytes = FileUtil.readFileByBytes(imgPath);
			String image = Base64Util.encode(bytes);
			String lastResult = FaceSearch.search(image, groups);
			// 服务器内部跳转
			JSONObject o = JSONObject.parseObject(lastResult);
			int code = o.getIntValue("error_code");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			if (code == 0) {
				// 搜索到图片，可以确认员工身份，验证权限，有就开门，无权限则提示
				// 假设为会议室一准备上传的图片
				String devNum = "100001";
				// 拿到人脸搜索结果中的用户id，判断此用户是否有打开会议室一的权限
				String temp = o.getString("result");
				JSONObject obj001 = JSONObject.parseObject(temp);
				String userList = obj001.getString("user_list");
				JSONArray arr = JSONArray.parseArray(userList);
				int count = 0;
				String userId = "";
				for (int i = 0; i < arr.size(); i++) {
					JSONObject t = arr.getJSONObject(i);
					int score = t.getIntValue("score");
					if (score > count) {
						count = score;
						userId = t.getString("user_id");
					}
				}
				if (count >= 80) {
					EmpDeviceDAO ed = new EmpDeviceDAO();
					boolean flag = ed.checkAuthInfo(devNum, userId);
					if (flag) {
						pw.write("有权限，可以开门");
					} else {
						// 失败
						pw.write("无权限，不可以开门");
					}
				} else {
					pw.write("无发验证身份信息，不可以开门");
				}
			} else {
				pw.write("非法人员，不可以开门");
			}
			pw.flush();
			pw.close();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
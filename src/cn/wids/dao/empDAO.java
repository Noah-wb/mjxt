package cn.wids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cn.wids.dto.empDTO;
import cn.wids.utils.DBUtil;

public class empDAO {
	public ArrayList<empDTO> getuserlist() {
		ArrayList<empDTO> list=new ArrayList<empDTO>();
		try {
			
			//获取数据库连接
			Connection conn=DBUtil.getconn();
			//准备数据库
			String sql="select * from tb_emp";
			//预编译sql语句
			PreparedStatement ps=conn.prepareStatement(sql);
			//执行
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				String empNum=rs.getString("emp_num");
				String empName=rs.getString("emp_name");
				String empSex=rs.getString("emp_sex");
				int empAge=rs.getInt("emp_age");
				String empDapt=rs.getString("emp_dept");
				empDTO dto=new empDTO(empNum, empName, empSex, empAge, empDapt);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public empDTO getuserinfo(String num,String name)
	{
		empDTO dto=null;
		
		try {
			//1.获取数据库连接
			Connection conn= DBUtil.getconn();
			//2.准备SQL语句
			String sql="select emp_num,emp_name from tb_emp where emp_num=? and emp_name=?";
			//3.预编译sql语句
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, num);
			ps.setString(2, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 num=rs.getString("emp_num");
				 name=rs.getString("emp_name");
				
				 dto=new empDTO(num, name, null, 0, null);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	public boolean insertEmpInfo(empDTO dto) {
		
		try {
			Connection conn= DBUtil.getconn();
			String sql="insert into tb_emp values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getEmpNum());
			ps.setString(2, dto.getEmpName());
			ps.setString(3, dto.getEmpSex());
			ps.setInt(4, dto.getEmpAge());
			ps.setString(5, dto.getEmpDept());
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean getempdelete(String empNum)
	{
		
		try {
			//1.获取数据库连接
			Connection conn= DBUtil.getconn();
			//2.准备SQL语句
			String sql="delete from tb_emp where emp_num=?";
			//3.预编译sql语句
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, empNum);
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateemp(empDTO user) {
		boolean flag = false;

		try {
			Connection c = DBUtil.getconn();
			String sql = "update tb_emp set emp_name=? where emp_num=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, user.getEmpName());
			ps.setString(2, user.getEmpNum());
			int i = ps.executeUpdate();
			flag = i > 0 ? true : false;
			DBUtil.closeConn(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}

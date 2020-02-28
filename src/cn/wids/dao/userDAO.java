package cn.wids.dao;

import java.sql.Connection;
import java.util.ArrayList;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.wids.dto.userDTO;
import cn.wids.utils.DBUtil;

public class userDAO {
	
	public ArrayList<userDTO> getuserlist() {
		ArrayList<userDTO> list=new ArrayList<userDTO>();
		try {
			
			//获取数据库连接
			Connection conn=DBUtil.getconn();
			//准备数据库
			String sql="select * from tb_user";
			//预编译sql语句
			PreparedStatement ps=conn.prepareStatement(sql);
			//执行
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				String userName=rs.getString("user_name");
				String userPass=rs.getString("user_pass");
				String userSex=rs.getString("user_sex");
				String userAddr=rs.getString("user_addr");
				userDTO dto=new userDTO(userName, userPass, userSex, userAddr);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public userDTO getuserinfo(String name,String pass)
	{
		userDTO dto=null;
		
		try {
			//1.获取数据库连接
			Connection conn= DBUtil.getconn();
			//2.准备SQL语句
			String sql="select * from tb_user where user_name=? and user_pass=?";
			//3.预编译sql语句
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String userName=rs.getString("user_name");
				String userPass=rs.getString("user_pass");
				String userSex=rs.getString("user_sex");
				String userAddr=rs.getString("user_addr");
				 dto=new userDTO(userName, userPass, userSex, userAddr);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
}

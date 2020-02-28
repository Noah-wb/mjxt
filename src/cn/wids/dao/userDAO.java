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
			
			//��ȡ���ݿ�����
			Connection conn=DBUtil.getconn();
			//׼�����ݿ�
			String sql="select * from tb_user";
			//Ԥ����sql���
			PreparedStatement ps=conn.prepareStatement(sql);
			//ִ��
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
			//1.��ȡ���ݿ�����
			Connection conn= DBUtil.getconn();
			//2.׼��SQL���
			String sql="select * from tb_user where user_name=? and user_pass=?";
			//3.Ԥ����sql���
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

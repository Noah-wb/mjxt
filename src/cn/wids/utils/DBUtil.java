package cn.wids.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String URL="jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8";
	private static final String USERNAME="root";
	private static final String USERPASS="123456";
	private static Connection conn;
	private DBUtil(){
		
		
	}
	public static Connection getconn() {
		if (conn==null) {
			try {
				conn=DriverManager.getConnection(URL, USERNAME,USERPASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

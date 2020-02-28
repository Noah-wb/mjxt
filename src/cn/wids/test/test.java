package cn.wids.test;

import  java.sql.Connection;

import cn.wids.utils.DBUtil;

public class test {
public static void main(String[] args) {
	
	Connection conn=DBUtil.getconn();
	System.out.println(conn);
}
}

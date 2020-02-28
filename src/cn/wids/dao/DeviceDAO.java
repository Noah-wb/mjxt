package cn.wids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.wids.dto.DeviceDTO;
import cn.wids.utils.DBUtil;

public class DeviceDAO {
	
	/**
	 * 查询所有设备信息
	 * @return
	 */
	public ArrayList<DeviceDTO> getDeviceList(){
		ArrayList<DeviceDTO> list = new ArrayList<DeviceDTO>();
		try {
	//		从数据库获取数据，组装到dto对象中，存入list集合内
	//		1、获取到数据库的连接
			Connection conn = DBUtil.getconn();
	//		2、准备sql
			String sql = "select * from tb_device";
	//		3、预编译sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
//			4、执行
			ResultSet rs = ps.executeQuery();
//			5、处理结果集
			while(rs.next()){
				String devNum = rs.getString("dev_num");
				String devName = rs.getString("dev_name");
				String devDoor = rs.getString("dev_door");
				String remarks = rs.getString("remarks");
				DeviceDTO dto = new DeviceDTO(devNum, devName, devDoor, remarks);
				list.add(dto);
			}
//			6、关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据设备编号查询设备信息
	 * @param devNum
	 * @return
	 */
	public DeviceDTO getDeviceInfo(String devNum){
		DeviceDTO dto = null;
		try {
			//		从数据库获取数据，组装到dto对象中，存入list集合内
			//		1、获取到数据库的连接
			Connection conn = DBUtil.getconn();
			//		2、准备sql
			String sql = "select * from tb_device where dev_num=?";
			//		3、预编译sql语句
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, devNum);
//			4、执行
			ResultSet rs = ps.executeQuery();
//			5、处理结果集
			while(rs.next()){
				 devNum = rs.getString("dev_num");
				String devName = rs.getString("dev_name");
				String devDoor = rs.getString("dev_door");
				String remarks = rs.getString("remarks");
				dto = new DeviceDTO(devNum, devName, devDoor, remarks);
			}
//			6、关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	/**
	 * 添加设备信息
	 * @param dto
	 * @return
	 */
	public boolean insertDeviceInfo(DeviceDTO dto){
		try {
			Connection conn = DBUtil.getconn();
			String sql = "insert into tb_device(dev_num,dev_name,dev_door,remarks) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getDevNum());
			ps.setString(2, dto.getDevName());
			ps.setString(3, dto.getDevDoor());
			ps.setString(4, dto.getRemarks());
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改设备信息
	 * @param dto
	 * @return
	 */
	public boolean updateDeviceInfo(DeviceDTO dto){
		try {
			Connection conn = DBUtil.getconn();
			String sql = "update tb_device set dev_num=?,dev_name=?,dev_door=?,remarks=? where dev_num=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getDevNum());
			ps.setString(2, dto.getDevName());
			ps.setString(3, dto.getDevDoor());
			ps.setString(4, dto.getRemarks());
			ps.setString(5, dto.getDevNum());
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据设备编号删除设备信息
	 * @param devNum
	 * @return
	 */
	public boolean deleteDeviceInfo(String devNum){
		try {
			Connection conn = DBUtil.getconn();
			String sql = "delete from tb_device where dev_num=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, devNum);
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

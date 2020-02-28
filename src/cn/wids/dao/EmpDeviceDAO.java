package cn.wids.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.wids.dto.DeviceDTO;
import cn.wids.dto.EmpDevDTO;
import cn.wids.dto.empDTO;
import cn.wids.utils.DBUtil;

public class EmpDeviceDAO {

	/**
	 * ��ѯ������ԱȨ����Ϣ
	 * @return
	 */
	public List<EmpDevDTO> getEmpDevList(){
		ArrayList<EmpDevDTO> list = new ArrayList<EmpDevDTO>();
		try {
	//		�����ݿ��ȡ���ݣ���װ��dto�����У�����list������
	//		1����ȡ�����ݿ������
			Connection conn = DBUtil.getconn();
	//		2��׼��sql
			String sql = "select td.dev_num,td.dev_name,td.dev_door,"
					+ "te.emp_num,te.emp_name from tb_emp_dev ted "
					+ "left join tb_device td on td.dev_num=ted.dev_num "
					+ "LEFT join tb_emp te on te.emp_num=ted.emp_num";
	//		3��Ԥ����sql���
			PreparedStatement ps = conn.prepareStatement(sql);
//			4��ִ��
			ResultSet rs = ps.executeQuery();
//			5����������
			while(rs.next()){
				String devNum = rs.getString("dev_num");
				String devName = rs.getString("dev_name");
				String devDoor = rs.getString("dev_door");
				String empNum = rs.getString("emp_num");
				String empName = rs.getString("emp_name");
				DeviceDTO dev = new DeviceDTO(devNum, devName, devDoor, null);
				empDTO emp = new empDTO(empNum, empName, null, 0, null);
				
				EmpDevDTO dto = new EmpDevDTO();
				dto.setDev(dev);
				dto.setEmp(emp);
				list.add(dto);
			}
//			6���ر���Դ
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * �����豸��Ų�ѯ�豸��Ϣ
	 * @param devNum
	 * @return
	 */
	/*public DeviceDTO getDeviceInfo(String _devNum){
		DeviceDTO dto = null;
		try {
			//		�����ݿ��ȡ���ݣ���װ��dto�����У�����list������
			//		1����ȡ�����ݿ������
			Connection conn = DBUtil.getConnection();
			//		2��׼��sql
			String sql = "select * from tb_device where dev_num=?";
			//		3��Ԥ����sql���
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, _devNum);
//			4��ִ��
			ResultSet rs = ps.executeQuery();
//			5����������
			while(rs.next()){
				String devNum = rs.getString("dev_num");
				String devName = rs.getString("dev_name");
				String devDoor = rs.getString("dev_door");
				String remarks = rs.getString("remarks");
				dto = new DeviceDTO(devNum, devName, devDoor, remarks);
			}
//			6���ر���Դ
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}*/
	
	/**
	 * ���Ȩ����Ϣ
	 * @param dto
	 * @return
	 */
	public boolean insertDeviceInfo(String empNum,String devNum){
		try {
			Connection conn = DBUtil.getconn();
			String sql = "insert into tb_emp_dev(dev_num,emp_num) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, devNum);
			ps.setString(2, empNum);
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
	 * �����豸��ź�Ա�����ɾ��Ȩ����Ϣ
	 * @param devNum
	 * @return
	 */
	public boolean deleteDeviceInfo(String devNum,String empNum){
		try {
			Connection conn = DBUtil.getconn();
			String sql = "delete from tb_emp_dev where dev_num=? and emp_num=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, devNum);
			ps.setString(2, empNum);
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkAuthInfo(String devNum,String empNum){
		try {
			//		�����ݿ��ȡ���ݣ���װ��dto�����У�����list������
			//		1����ȡ�����ݿ������
			Connection conn = DBUtil.getconn();
			//		2��׼��sql
			String sql = "select * from tb_emp_dev where dev_num=? and emp_num=?";
			//		3��Ԥ����sql���
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, devNum);
			ps.setString(2, empNum);
//			4��ִ��
			ResultSet rs = ps.executeQuery();
//			5����������
			while(rs.next()){
				return true;
			}
//			6���ر���Դ
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
package cn.wids.dto;

public class DeviceDTO {
	private String devNum;
	private String devName;
	private String devDoor;
	private String remarks;
	public String getDevNum() {
		return devNum;
	}
	public void setDevNum(String devNum) {
		this.devNum = devNum;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDevDoor() {
		return devDoor;
	}
	public void setDevDoor(String devDoor) {
		this.devDoor = devDoor;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public DeviceDTO(String devNum, String devName, String devDoor, String remarks) {
		super();
		this.devNum = devNum;
		this.devName = devName;
		this.devDoor = devDoor;
		this.remarks = remarks;
	}
	public DeviceDTO(){
		
	}
}

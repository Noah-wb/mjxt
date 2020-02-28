package cn.wids.dto;

public class userDTO {
	private String userPass;
	private String userName;
	private String userSex;
	private String userAddr;
	public String getUserId() {
		return userPass;
	}
	public void setUserId(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public userDTO(String userPass, String userName, String userSex, String userAddr) {
		super();
		this.userPass = userPass;
		this.userName = userName;
		this.userSex = userSex;
		this.userAddr = userAddr;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
}

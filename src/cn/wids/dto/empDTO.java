package cn.wids.dto;

public class empDTO {
	private String empName;
	private String empSex;
	private int empAge;
	private String empDept;
	private String empNum;
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public empDTO(String empNum ,String empName, String empSex, int empAge, String empDept) {
		super();
		this.empName = empName;
		this.empSex = empSex;
		this.empAge = empAge;
		this.empDept = empDept;
		this.empNum = empNum;
	}
	public empDTO(){
		
	}
}

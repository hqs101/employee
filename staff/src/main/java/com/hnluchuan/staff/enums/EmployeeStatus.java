package com.hnluchuan.staff.enums;

/**
 * 员工 员工状态/1.录用/2.停职
 */
public enum EmployeeStatus {
    // 请根据实际情况改成对应的值和备注
	Status1(1, "录用"),
	Status2(2, "停职");

	private int value;
    private String remark;
    
    private EmployeeStatus(int value, String remark) {
    	this.value = value;
    	this.remark = remark;
    }

	public void setValue(int value) {
		this.value = value;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public int getValue() {
    	return value;
    }
    public String getRemark() {
    	return remark;
    }
    
    public static EmployeeStatus getByValue(int value) {
    	for (EmployeeStatus o : EmployeeStatus.values()) {
    		if (o.getValue() == value) {
    			return o;
    		}
    	}
    	return null;
    }
}

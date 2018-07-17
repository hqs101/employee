package com.hnluchuan.staff.common;

public enum RoleCode {
	Factory("factory", "工厂角色"); 

	private String code;
	private String remark;

	private RoleCode(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public String getRemark() {
		return remark;
	}

}

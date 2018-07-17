package com.hnluchuan.staff.common;

public enum PayBizType {
	Buy(1, "购物"), 
	FactoryRegister(2, "工厂入驻"),
	CompanyRegister(3, "新增设计公司"),
	SubAccountRegister(4, "新增子账号");

	private int value;
	private String remark;

	private PayBizType(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public static PayBizType getByValue(int value) {
		for (PayBizType o : PayBizType.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}

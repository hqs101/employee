package com.hnluchuan.staff.common;

public enum PayStatus {
	NotPay(0, "未支付"), 
	Payed(1, "已支付");

	private int value;
	private String remark;

	private PayStatus(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public static PayStatus getByValue(int value) {
		for (PayStatus o : PayStatus.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}

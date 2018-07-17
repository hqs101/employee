package com.hnluchuan.staff.common;

public enum PayType {
	WX(0, "微信支付"),
	Alipay(1, "支付宝支付"),
	Zhongjin(2, "中金支付");

	private int value;
	private String remark;

	private PayType(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public int getValue() {
		return value;
	}

	public String getRemark() {
		return remark;
	}

	public static PayType getByValue(int value) {
		for (PayType o : PayType.values()) {
			if (o.getValue() == value) {
				return o;
			}
		}
		return null;
	}
}

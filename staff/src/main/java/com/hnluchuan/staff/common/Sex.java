package com.hnluchuan.staff.common;

/*
public enum Sex {
	Male(0),
	Female(1),
	Unknow(2);
	
	private int value;
	
	private Sex(int value) {
		this.value = value;
    }
	
	public int value() {
		return value;
	}
	
	public static String 
} */

public enum Sex {
    Male(1, "男"), 
    Female(2, "女"),
    Unknow(0, "未知");

    private int value;
    private String remark;

    private Sex(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public static Sex getByValue(int value) {
        for (Sex o : Sex.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
}

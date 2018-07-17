package com.hnluchuan.staff.test.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.hnluchuan.core.model.BaseModel;

public class TestDTO extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 参数名
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 参数值
	 */
	@Column(name = "value")
    private String value;
	/**
	 * 备注
	 */
	@Column(name = "remark")
    private String remark;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name = "createdTime")
    private Date createdTime;
	/**
	 * 是否是富文本
	 */
	@Column(name = "isRichText")
    private Boolean isRichText;
	
	public Long getId() {
    	return id;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
	
	public String getName() {
    	return name;
    }
	
    public void setName(String name) {
    	this.name = name;
    }
	
	public String getValue() {
    	return value;
    }
	
    public void setValue(String value) {
    	this.value = value;
    }
	
	public String getRemark() {
    	return remark;
    }
	
    public void setRemark(String remark) {
    	this.remark = remark;
    }
	
	public Date getCreatedTime() {
    	return createdTime;
    }
	
    public void setCreatedTime(Date createdTime) {
    	this.createdTime = createdTime;
    }
	
	public Boolean getIsRichText() {
    	return isRichText;
    }
	
    public void setIsRichText(Boolean isRichText) {
    	this.isRichText = isRichText;
    }
	
}

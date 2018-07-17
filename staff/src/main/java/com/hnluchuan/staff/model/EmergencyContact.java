package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hnluchuan.core.model.BaseModel;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import com.hnluchuan.staff.model.Employee;

/**
 * 紧急联系人
 */
@Entity
@Table(name = "emergency_contact") 
public class EmergencyContact extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	public EmergencyContact() {
	}
	
	public EmergencyContact(Long id) {
		this.id = id;
	}
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	/**
	 * 员工ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	/**
	 * 姓名
	 */
	@Column(name = "name")    		
	private String name;
	/**
	 * 关系
	 */
	@Column(name = "relation")    		
	private String relation;
	/**
	 * 联系电话
	 */
	@Column(name = "phone")    		
	private String phone;
	/**
	 * 单位或住址
	 */
	@Column(name = "address")    		
	private String address;
	// fields end
	
	/** id */
	public Long getId() {
    	return id;
    }
    /** id */
    public void setId(Long id) {
    	this.id = id;
    }
	/** 员工ID */
	public Employee getEmployee() {
    	return employee;
    }
    /** 员工ID */
    public void setEmployee(Employee employee) {
    	this.employee = employee;
    }
	/** 姓名 */
	public String getName() {
    	return name;
    }
    /** 姓名 */
    public void setName(String name) {
    	this.name = name;
    }
	/** 关系 */
	public String getRelation() {
    	return relation;
    }
    /** 关系 */
    public void setRelation(String relation) {
    	this.relation = relation;
    }
	/** 联系电话 */
	public String getPhone() {
    	return phone;
    }
    /** 联系电话 */
    public void setPhone(String phone) {
    	this.phone = phone;
    }
	/** 单位或住址 */
	public String getAddress() {
    	return address;
    }
    /** 单位或住址 */
    public void setAddress(String address) {
    	this.address = address;
    }
	
}

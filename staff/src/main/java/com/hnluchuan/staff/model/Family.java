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
 * 家庭成员
 */
@Entity
@Table(name = "family") 
public class Family extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	public Family() {
	}
	
	public Family(Long id) {
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
	 * 联系方式
	 */
	@Column(name = "contact")    		
	private String contact;
	/**
	 * 年龄
	 */
	@Column(name = "age")    		
	private Integer age;
	/**
	 * 工作单位
	 */
	@Column(name = "company")    		
	private String company;
	/**
	 * 职位
	 */
	@Column(name = "position")    		
	private String position;
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
	/** 联系方式 */
	public String getContact() {
    	return contact;
    }
    /** 联系方式 */
    public void setContact(String contact) {
    	this.contact = contact;
    }
	/** 年龄 */
	public Integer getAge() {
    	return age;
    }
    /** 年龄 */
    public void setAge(Integer age) {
    	this.age = age;
    }
	/** 工作单位 */
	public String getCompany() {
    	return company;
    }
    /** 工作单位 */
    public void setCompany(String company) {
    	this.company = company;
    }
	/** 职位 */
	public String getPosition() {
    	return position;
    }
    /** 职位 */
    public void setPosition(String position) {
    	this.position = position;
    }
	
}

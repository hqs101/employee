package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hnluchuan.core.model.BaseModel;

import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.springframework.format.annotation.DateTimeFormat;
import com.hnluchuan.staff.model.Employee;

/**
 * 工作经验
 */
@Entity
@Table(name = "experience") 
public class Experience extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	public Experience() {
	}
	
	public Experience(Long id) {
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
	 * 离职原因
	 */
	@Column(name = "reason")    		
	private String reason;
	/**
	 * 单位
	 */
	@Column(name = "company")    		
	private String company;
	/**
	 * 工作
	 */
	@Column(name = "work")    		
	private String work;
	/**
	 * 职位
	 */
	@Column(name = "position")    		
	private String position;
	/**
	 * 工作起始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start")
	private Date start;
	/**
	 * 工作结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end")
	private Date end;
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
	/** 离职原因 */
	public String getReason() {
    	return reason;
    }
    /** 离职原因 */
    public void setReason(String reason) {
    	this.reason = reason;
    }
	/** 单位 */
	public String getCompany() {
    	return company;
    }
    /** 单位 */
    public void setCompany(String company) {
    	this.company = company;
    }
	/** 工作 */
	public String getWork() {
    	return work;
    }
    /** 工作 */
    public void setWork(String work) {
    	this.work = work;
    }
	/** 职位 */
	public String getPosition() {
    	return position;
    }
    /** 职位 */
    public void setPosition(String position) {
    	this.position = position;
    }
	/** 工作起始时间 */
	public Date getStart() {
    	return start;
    }
    /** 工作起始时间 */
    public void setStart(Date start) {
    	this.start = start;
    }
	/** 工作结束时间 */
	public Date getEnd() {
    	return end;
    }
    /** 工作结束时间 */
    public void setEnd(Date end) {
    	this.end = end;
    }
	
}

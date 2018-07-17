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
 * 学历
 */
@Entity
@Table(name = "education")
public class Education extends BaseModel {
	private static final long serialVersionUID = 1L;

	public Education() {
	}

	public Education(Long id) {
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
	 * 学习起始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start")
	private Date start;
	/**
	 * 学习结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end")
	private Date end;
	/**
	 * 专业
	 */
	@Column(name = "major")
	private String major;
	/**
	 * 学历
	 */
	@Column(name = "record")
	private Integer record;
	/**
	 * 证书
	 */
	@Column(name = "certificate")
	private String certificate;
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
	/** 学习起始时间 */
	public Date getStart() {
		return start;
	}
	/** 学习起始时间 */
	public void setStart(Date start) {
		this.start = start;
	}
	/** 学习结束时间 */
	public Date getEnd() {
		return end;
	}
	/** 学习结束时间 */
	public void setEnd(Date end) {
		this.end = end;
	}
	/** 专业 */
	public String getMajor() {
		return major;
	}
	/** 专业 */
	public void setMajor(String major) {
		this.major = major;
	}
	/** 学历 */
	public Integer getRecord() {
		return record;
	}
	/** 学历 */
	public void setRecord(Integer record) {
		this.record = record;
	}
	/** 证书 */
	public String getCertificate() {
		return certificate;
	}
	/** 证书 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}

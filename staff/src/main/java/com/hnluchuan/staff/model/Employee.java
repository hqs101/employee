package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hnluchuan.core.model.BaseModel;

import javax.persistence.ManyToOne;
import com.hnluchuan.staff.model.User;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工
 */
@Entity
@Table(name = "employee") 
public class Employee extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	public Employee() {
	}
	
	public Employee(Long id) {
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
	 * 用户ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 岗位
	 */
	@Column(name = "position")    		
	private String position;
	/**
	 * 姓名
	 */
	@Column(name = "name")    		
	private String name;
	/**
	 * 出生日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birth")
	private Date birth;
	/**
	 * 性别
	 */
	@Column(name = "sex")    		
	private Integer sex;
	/**
	 * 身高
	 */
	@Column(name = "height")    		
	private Double height;
	/**
	 * 体重
	 */
	@Column(name = "weight")    		
	private Double weight;
	/**
	 * 籍贯
	 */
	@Column(name = "place")    		
	private String place;
	/**
	 * 民族
	 */
	@Column(name = "nation")    		
	private String nation;
	/**
	 * 联系电话
	 */
	@Column(name = "phone")    		
	private String phone;

	public String getiDCard() {
		return iDCard;
	}

	public void setiDCard(String iDCard) {
		this.iDCard = iDCard;
	}

	/**
	 * 身份证号
	 */
	@Column(name = "ID_card")    		
	private String iDCard;
	/**
	 * 毕业学校
	 */
	@Column(name = "school")    		
	private String school;
	/**
	 * 专业
	 */
	@Column(name = "major")    		
	private String major;
	/**
	 * 学历
	 */
	@Column(name = "education")    		
	private Integer education;
	/**
	 * 户口所在地
	 */
	@Column(name = "location")    		
	private String location;
	/**
	 * 居住地址
	 */
	@Column(name = "address")    		
	private String address;
	/**
	 * 微信号
	 */
	@Column(name = "wechat")    		
	private String wechat;
	/**
	 * 邮箱
	 */
	@Column(name = "email")    		
	private String email;
	/**
	 * 婚姻状况
	 */
	@Column(name = "marry")    		
	private Integer marry;
	/**
	 * 入职时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "in_time")
	private Date inTime;
	/**
	 * 员工状态/1.录用/2.停职
	 */
	@Column(name = "status")    		
	private Integer status;
	// fields end
	
	/** id */
	public Long getId() {
    	return id;
    }
    /** id */
    public void setId(Long id) {
    	this.id = id;
    }
	/** 用户ID */
	public User getUser() {
    	return user;
    }
    /** 用户ID */
    public void setUser(User user) {
    	this.user = user;
    }
	/** 岗位 */
	public String getPosition() {
    	return position;
    }
    /** 岗位 */
    public void setPosition(String position) {
    	this.position = position;
    }
	/** 姓名 */
	public String getName() {
    	return name;
    }
    /** 姓名 */
    public void setName(String name) {
    	this.name = name;
    }
	/** 出生日期 */
	public Date getBirth() {
    	return birth;
    }
    /** 出生日期 */
    public void setBirth(Date birth) {
    	this.birth = birth;
    }
	/** 性别 */
	public Integer getSex() {
    	return sex;
    }
    /** 性别 */
    public void setSex(Integer sex) {
    	this.sex = sex;
    }
	/** 身高 */
	public Double getHeight() {
    	return height;
    }
    /** 身高 */
    public void setHeight(Double height) {
    	this.height = height;
    }
	/** 体重 */
	public Double getWeight() {
    	return weight;
    }
    /** 体重 */
    public void setWeight(Double weight) {
    	this.weight = weight;
    }
	/** 籍贯 */
	public String getPlace() {
    	return place;
    }
    /** 籍贯 */
    public void setPlace(String place) {
    	this.place = place;
    }
	/** 民族 */
	public String getNation() {
    	return nation;
    }
    /** 民族 */
    public void setNation(String nation) {
    	this.nation = nation;
    }
	/** 联系电话 */
	public String getPhone() {
    	return phone;
    }
    /** 联系电话 */
    public void setPhone(String phone) {
    	this.phone = phone;
    }
	/** 身份证号 */

    /** 身份证号 */

	/** 毕业学校 */
	public String getSchool() {
    	return school;
    }
    /** 毕业学校 */
    public void setSchool(String school) {
    	this.school = school;
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
	public Integer getEducation() {
    	return education;
    }
    /** 学历 */
    public void setEducation(Integer education) {
    	this.education = education;
    }
	/** 户口所在地 */
	public String getLocation() {
    	return location;
    }
    /** 户口所在地 */
    public void setLocation(String location) {
    	this.location = location;
    }
	/** 居住地址 */
	public String getAddress() {
    	return address;
    }
    /** 居住地址 */
    public void setAddress(String address) {
    	this.address = address;
    }
	/** 微信号 */
	public String getWechat() {
    	return wechat;
    }
    /** 微信号 */
    public void setWechat(String wechat) {
    	this.wechat = wechat;
    }
	/** 邮箱 */
	public String getEmail() {
    	return email;
    }
    /** 邮箱 */
    public void setEmail(String email) {
    	this.email = email;
    }
	/** 婚姻状况 */
	public Integer getMarry() {
    	return marry;
    }
    /** 婚姻状况 */
    public void setMarry(Integer marry) {
    	this.marry = marry;
    }
	/** 入职时间 */
	public Date getInTime() {
    	return inTime;
    }
    /** 入职时间 */
    public void setInTime(Date inTime) {
    	this.inTime = inTime;
    }
	/** 员工状态/1.录用/2.停职 */
	public Integer getStatus() {
    	return status;
    }
    /** 员工状态/1.录用/2.停职 */
    public void setStatus(Integer status) {
    	this.status = status;
    }
	
}

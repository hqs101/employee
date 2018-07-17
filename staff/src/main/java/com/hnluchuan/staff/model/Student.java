package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hnluchuan.core.model.BaseModel;


/**
 * 学生
 */
@Entity
@Table(name = "sys_student") 
public class Student extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	public Student() {
	}
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	/**
	 * 姓名
	 */
	@Column(name = "name")    		
	private String name;
	/**
	 * 年龄
	 */
	@Column(name = "age")    		
	private Integer age;
	// fields end
	
	/**  */
	public Long getId() {
    	return id;
    }
    /**  */
    public void setId(Long id) {
    	this.id = id;
    }
	/** 姓名 */
	public String getName() {
    	return name;
    }
    /** 姓名 */
    public void setName(String name) {
    	this.name = name;
    }
	/** 年龄 */
	public Integer getAge() {
    	return age;
    }
    /** 年龄 */
    public void setAge(Integer age) {
    	this.age = age;
    }
	// getter/setter end
	
	/*
	
	// 
	d.setid();
	
	// 姓名
	d.setname();
	
	// 年龄
	d.setage();
	*/
}

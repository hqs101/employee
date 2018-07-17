package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hnluchuan.core.model.BaseModel;

/**
 * District
 */
@Entity
@Table(name = "sys_district") 
public class District extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 地区名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 城市
	 */
	@ManyToOne
	@JoinColumn(name = "city_id")
    private City city;
	/**
	 * 字母
	 */
	@Column(name = "letter")
    private String letter;
	/**
	 * 全拼音
	 */
	@Column(name = "fullletter")
    private String fullLetter;
	
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
	
	public City getCity() {
    	return city;
    }
	
    public void setCity(City city) {
    	this.city = city;
    }
	
	public String getLetter() {
    	return letter;
    }
	
    public void setLetter(String letter) {
    	this.letter = letter;
    }
	
	public String getFullLetter() {
    	return fullLetter;
    }
	
    public void setFullLetter(String fullLetter) {
    	this.fullLetter = fullLetter;
    }
	
}

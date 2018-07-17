package com.hnluchuan.staff.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hnluchuan.core.model.BaseModel;

/**
 * City
 */
@Entity
@Table(name = "sys_city") 
public class City extends BaseModel {
	/**
	 * id
	 */
	@Id
	@GeneratedValue
	@Column(name = "id")
    private Long id;
	/**
	 * 名称
	 */
	@Column(name = "name")
    private String name;
	/**
	 * 省份
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_id")
    private Province province;
	/**
	 * 是否是热门城市
	 */
	@Column(name = "isHot")
    private Boolean isHot;
	/**
	 * 全拼音
	 */
	@Column(name = "fullletter")
    private String fullletter;
	/**
	 * 首字母
	 */
	@Column(name = "letter")
    private String letter;
	
	@OneToMany(mappedBy="city", fetch = FetchType.LAZY)
	private List<District> districts;
	
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
	
	public Province getProvince() {
    	return province;
    }
	
    public void setProvince(Province province) {
    	this.province = province;
    }
	
	public Boolean getIsHot() {
    	return isHot;
    }
	
    public void setIsHot(Boolean isHot) {
    	this.isHot = isHot;
    }
	
	public String getFullletter() {
    	return fullletter;
    }
	
    public void setFullletter(String fullletter) {
    	this.fullletter = fullletter;
    }
	
	public String getLetter() {
    	return letter;
    }
	
    public void setLetter(String letter) {
    	this.letter = letter;
    }

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
    
	
}

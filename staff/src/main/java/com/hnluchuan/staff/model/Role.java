package com.hnluchuan.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hnluchuan.core.model.BaseModel;


/**
 * Role is necessary, for access control, etc. 
 * 
 * Aug 30, 2012
 *
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseModel {
   
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    /**
     * 描述
     */
    private String description;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "editable")
    private Boolean editable;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	
	/**
     * @return the description
     */
    public String getDescription() {
    	return description;
    }
	/**
     * @param description the description to set
     */
    public void setDescription(String description) {
    	this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
    
}

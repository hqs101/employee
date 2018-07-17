package com.hnluchuan.staff.model;

import com.hnluchuan.core.model.BaseModel;

public class BaseUser extends BaseModel {
	private Long id;

	/**
     * @return the id
     */
    public Long getId() {
    	return id;
    }

	/**
     * @param id the id to set
     */
    public void setId(Long id) {
    	this.id = id;
    }
	
	
}

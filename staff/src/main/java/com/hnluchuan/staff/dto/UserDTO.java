package com.hnluchuan.staff.dto;

import com.hnluchuan.staff.model.User;

public class UserDTO extends User {
	private String searchTerm;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
}

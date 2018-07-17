package com.hnluchuan.staff.dto;

import java.util.ArrayList;
import java.util.List;

import com.hnluchuan.staff.model.Authority;

public class ModuleAuthorityDTO {
	private String module;
	private List<Authority> authorities = new ArrayList<>();
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
}

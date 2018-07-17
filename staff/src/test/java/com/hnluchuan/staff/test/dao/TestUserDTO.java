package com.hnluchuan.staff.test.dao;

public class TestUserDTO {


	public TestUserDTO() {
	}
	public TestUserDTO(Long id) {
		this.id = id;
	}
	private Long id;
	private String name;

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
	
	

}

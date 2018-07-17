package com.hnluchuan.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hnluchuan.core.model.BaseModel;

@Entity
@Table(name = "example_user")
@SuppressWarnings("serial")
public class ExampleUser extends BaseModel {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private Double money;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}

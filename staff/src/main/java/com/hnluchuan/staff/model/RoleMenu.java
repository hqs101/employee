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
 * RoleMenu
 */
@Entity
@Table(name = "sys_rolemenu") 
public class RoleMenu extends BaseModel {
	
	public RoleMenu() {
	}
	
	public RoleMenu(Long id) {
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
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(name = "role_id")
    private Role role;
	/**
	 * 菜单
	 */
	@ManyToOne
	@JoinColumn(name = "menu_id")
    private Menu menu;
	
	public Long getId() {
    	return id;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
	
	public Role getRole() {
    	return role;
    }
	
    public void setRole(Role role) {
    	this.role = role;
    }
	
	public Menu getMenu() {
    	return menu;
    }
	
    public void setMenu(Menu menu) {
    	this.menu = menu;
    }
	
}


package com.hnluchuan.staff.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.hnluchuan.core.model.BaseModel;

/**
 * 
 * 
 * Sep 1, 2012
 *
 */
@Entity
@Table (name = "sys_menu")
public class Menu extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;
	/**
	 * starts with 0.
	 */
	private Integer level;
	private String name;
	private String href;
	/**
	 * this field is for dwz only.
	 */
	private String rel;

	/**
	 * 顺序哦
	 */
	private Integer seq;
	
	@OneToOne
	@JoinColumn(name = "authority_id")
	private Authority authority;

	@ManyToOne
	private Menu parent;
	@OneToMany(mappedBy = "parent")
	@OrderBy(value  = "level ASC, seq ASC")
	@Where(clause = "deleted = 0")
	private List<Menu> children;
	@Column(name = "`show`")
	private boolean show;
	private Boolean deleted;
	/**
	 * 图标，参见http://fontawesome.io/icons/
	 */
	private String icon;
	public Integer getLevel() {
    	return level;
    }
	public void setLevel(Integer level) {
    	this.level = level;
    }
	public String getName() {
    	return name;
    }
	public void setName(String name) {
    	this.name = name;
    }
	public String getHref() {
    	return href;
    }
	public void setHref(String href) {
    	this.href = href;
    }
	public String getRel() {
    	return rel;
    }
	public void setRel(String rel) {
    	this.rel = rel;
    }
	public Menu getParent() {
    	return parent;
    }
	public void setParent(Menu parent) {
    	this.parent = parent;
    }
	public List<Menu> getChildren() {
    	return children;
    }
	public void setChildren(List<Menu> children) {
    	this.children = children;
    }
	
	public void addChild(Menu child) {
		if (children == null) {
			children = new ArrayList<Menu>();
		}
		children.add(child);
	}
	
	/**
     * @return the authority
     */
    public Authority getAuthority() {
    	return authority;
    }
	/**
     * @param authority the authority to set
     */
    public void setAuthority(Authority authority) {
    	this.authority = authority;
    }
	public Integer getSeq() {
    	return seq;
    }
	public void setSeq(Integer seq) {
    	this.seq = seq;
    }
	/**
     * @return the show
     */
    public boolean getShow() {
    	return show;
    }
	/**
     * @param show the show to set
     */
    public void setShow(boolean show) {
    	this.show = show;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}

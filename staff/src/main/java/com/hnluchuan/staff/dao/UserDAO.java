package com.hnluchuan.staff.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.common.UserType;
import com.hnluchuan.staff.dto.UserDTO;
import com.hnluchuan.staff.model.User;

@Repository
public class UserDAO extends BaseDAO<User> {

    public List<User> find(UserDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From User n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getUsername())) {
            hql.append(" and n.username = ? ");
            paras.add(dto.getUsername());
        }					
        if (dto.getType() != null) {
            hql.append(" and n.type = ? ");
            paras.add(dto.getType());
        }
        //hql.append(" and n.deleted = 0 or n.deleted is null ");
        hql.append(" order by n.id desc ");
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    public User findOne(UserDTO dto) {
		Page page = new Page();
		page.setPageSize(1);
		page.setPage(1);
		
		List<User> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public User loadUserByUsername(String username) {
        UserDTO query = new UserDTO();
        query.setUsername(username);
        return this.findOne(query);
    }

    @Override
    public void update(User t) {
    	super.update(t);
    }
    
    @Override
    public void deleteById(Serializable id) {
    	String hql = "delete from  User where id = ?";
    	super.executeUpdate(hql, new Object[] {id});
    }
    
    public List<User> testNamedParameter() {
		String hql = "from User n where n.type in (:list)";
		List<Object> paras = new ArrayList<>();
		paras.add(Lists.newArrayList(UserType.Normal.getValue(), UserType.Manager.getValue()));
		return super.find(hql, paras.toArray(), new Page());
	}

	public List<UserDTO> testNewSqlQuery() {
		String sql = "select id, username from sys_user where id < ?";
		return super.findBySql(sql, new Object[] {100L}, new Page(), UserDTO.class);
	}
}

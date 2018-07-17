package com.hnluchuan.staff.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dto.MenuDTO;
import com.hnluchuan.staff.model.Menu;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * 
 * @date 2013-6-13
 *
 */
@Repository
public class MenuDAO extends BaseDAO<Menu> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Menu> find(MenuDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Menu n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getLevel() != null) {
        	hql.append(" and n.level = ? ");
        	paras.add(dto.getLevel());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
        	hql.append(" and n.name = ? ");
        	paras.add(dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getHref())) {
        	hql.append(" and n.href = ? ");
        	paras.add(dto.getHref());
        }
        if (dto.getOnlyShow() != null) {
            hql.append(" and n.show = true ");
        }
        
        hql.append(" and n.deleted = 0 ");
        
        hql.append(" order by n.level asc, n.seq asc ");
        
        return super.find(hql.toString(), paras.toArray(), page);
    }

	public Menu findRoot() {
		String hql = "from Menu n where n.parent is null ";
    	return super.findOne(hql, null);
	}
}

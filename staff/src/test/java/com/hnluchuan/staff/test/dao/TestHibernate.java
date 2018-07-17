package com.hnluchuan.staff.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.model.User;
import com.hnluchuan.staff.test.BaseTest;

public class TestHibernate extends BaseTest {

	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testDistinct() throws Exception {
		String hql = "select count(distinct u) from User u";
		
		int count = userDAO.countByHql(hql, null);
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testQueryTotalCount() {
		Integer count = userDAO.queryTotalCount("select count(*) from User", null);
		Assert.assertTrue(count > 0);
	}
	
	@Test
	public void testFindOneModelBySql() {
		User user = userDAO.findOneModelBySql("select * from sys_user where id = 1", null, User.class);
		Assert.assertTrue(user != null);
	}
}

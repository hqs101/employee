package com.hnluchuan.staff.test.dao;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hnluchuan.core.support.Page;
import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.model.User;
import com.hnluchuan.staff.test.BaseTest;

public class TestSql extends BaseTest {

	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testSql() throws Exception {
		String sql = "select id from sys_bizconfig";
		Page page = new Page();
		List<TestDTO> list = userDAO.findBySql(sql, null, page, TestDTO.class);
		System.out.println(list);
		System.out.println(ToStringBuilder.reflectionToString(page));
	}
	
}

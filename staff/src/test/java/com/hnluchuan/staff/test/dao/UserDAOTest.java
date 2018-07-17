package com.hnluchuan.staff.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnluchuan.staff.dao.UserDAO;
import com.hnluchuan.staff.dto.UserDTO;
import com.hnluchuan.staff.model.User;
import com.hnluchuan.staff.test.BaseTest;

public class UserDAOTest extends BaseTest {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void testFindOneUserDTO() {
		UserDTO dto = new UserDTO();
		dto.setId(1L);
		User user = userDAO.findOne(dto);
		assertEquals("username", "admin", user.getUsername());
	}
	
	@Test
	public void testNewDto() {
		String hql = "select new com.hnluchuan.staff.test.dao.TestUserDTO(u.id) from User u where id = 1";
		List<TestUserDTO> testUserDTOs = userDAO.find2(hql, null, null);
		assertEquals(1, testUserDTOs.size());
		assertEquals(1, testUserDTOs.get(0).getId().intValue());
	}
	
	@Test
	public void testSql() {
		String sql = "select * from sys_user where id = 1";
		List<User> users = sessionFactory.getCurrentSession().createNativeQuery(sql, User.class).list();
		assertEquals(1, users.size());
		assertEquals(1, users.get(0).getId().intValue());	
	}

	@Test
	public void testNamedParameter() {
		List<User> users = userDAO.testNamedParameter();
		Assert.assertTrue(users.size() > 0);
	}
	
	@Test
	public void testNewSqlQuery() {
		List<UserDTO> list = userDAO.testNewSqlQuery();
		Assert.assertTrue(list.size() > 0);
		//for (TestUserDTO u : list) {
			//System.out.println(ToStringBuilder.reflectionToString(u));
		//}
	}
}

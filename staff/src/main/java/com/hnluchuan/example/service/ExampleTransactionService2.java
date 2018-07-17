package com.hnluchuan.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.model.ExampleUser;

/**
 * 事务示例
 * @author kevin
 *
 */
@Service
public class ExampleTransactionService2 {

	@Autowired
	private ExampleUserDAO exampleUserDAO;
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void b_new() {
		ExampleUser user_b = new ExampleUser();
		user_b.setUsername("user_b");
		exampleUserDAO.create(user_b);
	}
	
	@Transactional(propagation = Propagation.NESTED)
	public void b_nested() {
		ExampleUser user_b = new ExampleUser();
		user_b.setUsername("user_b");
		exampleUserDAO.create(user_b);
	}
}

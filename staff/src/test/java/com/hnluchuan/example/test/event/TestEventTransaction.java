package com.hnluchuan.example.test.event;

import com.hnluchuan.example.model.ExampleUser;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.event.ExampleUserCreatedEvent;
import com.hnluchuan.staff.test.BaseTest;

public class TestEventTransaction extends BaseTest {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void test() {
		ExampleUser user = new ExampleUser();
		user.setUsername("kevin");
		Long id = exampleUserDAO.create(user);
		applicationContext.publishEvent(new ExampleUserCreatedEvent(id));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

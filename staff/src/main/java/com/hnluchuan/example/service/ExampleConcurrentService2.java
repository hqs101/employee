package com.hnluchuan.example.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.model.ExampleUser;

/**
 * 并发示例
 * @author kevin
 *
 */
@Service
public class ExampleConcurrentService2 {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    //@Transactional // 如果换成这个，TestAddMoneyOk1就会不成功
	public void addMoney(Long id, int amount) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExampleUser u = exampleUserDAO.load(id);
		u.setMoney(u.getMoney() + amount);
		exampleUserDAO.update(u);
		System.out.println("updated, money: " + u.getMoney());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

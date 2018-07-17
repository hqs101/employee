package com.hnluchuan.example.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.model.ExampleUser;

/**
 * 并发示例
 * @author kevin
 *
 */
@Service
public class ExampleConcurrentService {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;
	
	private static Lock lock = new ReentrantLock();
	
	@Autowired
	private ExampleConcurrentService2 service2;
	
	@Transactional
	public void addMoney(Long id, int amount) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			lock.lock();
			service2.addMoney(id, amount);
		} finally {
			lock.unlock();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void addMoney_issue(Long id, int amount) {
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

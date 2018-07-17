package com.hnluchuan.example.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.event.ExampleQueueInitEvent;
import com.hnluchuan.example.model.ExampleUser;

/**
 * 并发示例
 * @author kevin
 *
 */
@Service
public class ExampleConcurrentService3 {
	
	@Autowired
	private ApplicationContext applicationContext;
	private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(500); 
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;
	
	@PostConstruct
	public void init() {
		applicationContext.publishEvent(new ExampleQueueInitEvent(queue));
	}
	
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
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

	public ArrayBlockingQueue<Integer> getQueue() {
		return queue;
	}
    
}

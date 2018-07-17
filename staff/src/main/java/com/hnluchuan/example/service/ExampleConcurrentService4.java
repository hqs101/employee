package com.hnluchuan.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnluchuan.example.dao.ExampleUserDAO;

/**
 * 并发示例
 * @author kevin
 *
 */
@Service
public class ExampleConcurrentService4 {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;

	/**
	 * 有bug，可能会导致余额<0
	 * @param id
	 * @param amount
	 */
    @Transactional
	public void addMoney(Long id, double amount) {
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    	exampleUserDAO.addMoney(id, amount);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }

}

package com.hnluchuan.example.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.event.ExampleUserCreatedEvent;

//@Component // 要写这个@Component
public class ExampleUserCreatedListener1 implements ApplicationListener<ExampleUserCreatedEvent> {

	@Autowired
	private ExampleUserDAO exampleUserDAO;

	@Override
	public void onApplicationEvent(ExampleUserCreatedEvent event) {
		// 请仔细查看下面每个注释的方法里的注释。
		// 最终的解决办法参看ExampleUserCreatedListener2.java
	}

	/*
	 * 这种情况不会有问题，因为是和发起事件的地方在同一个线程里，是同一个事务里，所以不需要加@Transactional也能读到数据
	@Override
	public void onApplicationEvent(ExampleUserCreatedEvent event) {
		Long id = (Long) event.getSource();
		ExampleUser exampleUser = exampleUserDAO.load(id);
		System.out.println("loaded user: " + exampleUser.getUsername());
	} */
	
	/*
	 * 这种情况加了@Async，和发起事件的线程不是同一个线程，而是另起了一个线程，必须要加@Transactional，不然会取不到session，报错
	@Async
	@Override
	public void onApplicationEvent(ExampleUserCreatedEvent event) {
		Long id = (Long) event.getSource();
		ExampleUser exampleUser = exampleUserDAO.load(id);
		System.out.println("loaded user: " + exampleUser.getUsername());
	} */

	/**
	 * 这种情况虽然不会报取不到session的错误 ，但是会报No row with the given identifier exists: [com.hnluchuan.example.model.ExampleUser#5]这样的错误，
	 * 因为是异步处理消息的，所以在处理这个消息的时候，发起事件的那个方法可能还没有执行完，或者执行完了事务还没有提交，所以在这里读不到那个事务里的东西
	 *
	@Async
	@Transactional
	@Override
	public void onApplicationEvent(ExampleUserCreatedEvent event) {
		Long id = (Long) event.getSource();
		ExampleUser exampleUser = exampleUserDAO.load(id);
		System.out.println("loaded user: " + exampleUser.getUsername());
	} */
}

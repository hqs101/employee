package com.hnluchuan.example.event.listener;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.hnluchuan.example.dao.ExampleUserDAO;
import com.hnluchuan.example.event.ExampleUserCreatedEvent;
import com.hnluchuan.example.model.ExampleUser;

@Component
public class ExampleUserCreatedListener2 {
	
	@Autowired
	private ExampleUserDAO exampleUserDAO;

	/**
	 * @Async是异步处理，
	 * 如果要读写数据库必须要加@Transactional
	 * 
	 * @TransactionalEventListener可以指定classes，监听多个，如果方法里只有一个参数比如这里的 ExampleUserCreatedEvent event，可以不指定classes.
	 * phase默认就是after_commit，意思就是发送事件的那个事务提交了，这里才会执行。
	 * 另外，发起事务的地方必须要是在@Transactional里，不然这里得不到监听，比如发起的地方是
	 * 
	 * @Transactional // 如果这里不写这个@Transactional，那就进不到下面的监听方法里。
	    public String ab() throws Exception {
			ExampleUser user = new ExampleUser();
			user.setUsername("kevin" + System.currentTimeMillis());
			Long id = exampleUserDAO.create(user);
			applicationContext.publishEvent(new ExampleUserCreatedEvent(id));
			return ok();
		}
		
		因为是after_commit，所以，就算这里抛异常了，也不会回滚发起事务的地方的异常
	 * 
	 * @param event
	 */
	@Async
	@Transactional
	// @TransactionalEventListener(classes = {ExampleUserCreatedEvent.class}, phase = TransactionPhase.AFTER_COMMIT)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onApplicationEvent(ExampleUserCreatedEvent event) {
		System.out.println("进来了");
		Long id = (Long) event.getSource();
		ExampleUser exampleUser = exampleUserDAO.load(id);
		System.out.println("loaded user: " + exampleUser.getUsername());
	}
}

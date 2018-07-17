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
public class ExampleTransactionService {

	@Autowired
	private ExampleUserDAO exampleUserDAO;
	@Autowired
	private ExampleTransactionService2 service2;
	
	/**
	 * 测试 Propagation.REQUIRES_NEW 事务。
	 * 注意：a和b必须是在不同的类里，否则不起作用！
	 * a调b，如果，因为b的事务是Propagation.REQUIRES_NEW，所以b是一个独立的事务，和a这个事务没有关系，
	 * 所以，如果b成功了，a抛异常了，b也不会回滚；如果b回滚了，但是在调用b的地方catch住了异常，那么a不会受影响。
	 * 
	 */
	@Transactional
	public void a_new() {
		ExampleUser user_a = new ExampleUser();
		user_a.setUsername("user_a");
		exampleUserDAO.create(user_a);
		
		try {
			// b是一个独立的事务
			service2.b_new();
		} catch (Exception e) {
			// 如果b发生了异常，b的事务会回滚。如果在这里catch了异常，这个异常就不会影响a，不会导致a也回滚。如果没有catch住，那当然就会影响a了。
		}
		
		// 在这里抛异常只会回滚a，对b没有影响
		throw new RuntimeException("rollback a");
	}

	/**
	 * 不要想太多，HibernateTransactionManager不支持Nested事务。所以不要测了，不支持。
	 * 不信可以参考HibernateTransactionManager的类注释
	 * @see org.springframework.orm.hibernate4.HibernateTransactionManager
	 * 
	 * 嵌套事务（子事务）与REQUIRES_NEW事务的区别是，REQUIRES_NEW是完全可以自己提交和回滚的。
	 * 而子事务，自己不能提交，必须等到父事务提交了，才会被一起提交。如果父事务回滚了，子事务也会回滚。
	 * 那子事务有什么用呢？
	 */
	@Transactional
	public void a_nested() {
		ExampleUser user_a = new ExampleUser();
		user_a.setUsername("user_a");
		exampleUserDAO.create(user_a);
		
		try {
			// b是一个独立的事务
			service2.b_nested();
		} catch (Exception e) {
			// 如果b发生了异常，b的事务会回滚。如果在这里catch了异常，这个异常就不会影响a，不会导致a也回滚。如果没有catch住，那当然就会影响a了。
		}
		
		// 在这里抛异常只会回滚a，对b没有影响
		throw new RuntimeException("rollback a");
	}
	
}

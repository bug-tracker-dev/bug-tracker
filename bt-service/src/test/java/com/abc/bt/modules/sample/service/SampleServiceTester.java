package com.abc.bt.modules.sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.abc.bt.common.model.Page;
import com.abc.bt.common.test.SVCCommonTester;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;

public class SampleServiceTester extends SVCCommonTester {

	private static final Logger _LOG = Logger.getLogger(SampleServiceTester.class);

	// TODO define 2 svc: ASvc and BSvc

	@Resource(name = "userService")
	private IUserService userService;

	// TODO

	/**
	 * test the transaction propagations and rollbacks 7 cases need to be tested
	 * and 3 rollbacks <tx:method name="save*" propagation="REQUIRED"
	 * rollback-for="Exception"/> <tx:method name="remove*"
	 * propagation="REQUIRED" rollback-for="Exception" /> <tx:method
	 * name="update*" propagation="REQUIRED" rollback-for="Exception" />
	 * <tx:method name="get*" propagation="REQUIRED" /> <tx:method name="load*"
	 * propagation="REQUIRED" /> <tx:method name="exist*" propagation="REQUIRED"
	 * /> <tx:method name="*" read-only="true" />
	 */
	@Test
	public void save() {
		User user = new User();
		user.setId(80001L);
		user.setUsername("Tom");
		userService.saveUser(user);
	}

	@Test
	public void saveRollback() {

		try {
			User user = new User();
			user.setId(100001L);
			user.setUsername("Tom");
			userService.saveUserAndRollback(user);
		} catch (Exception e) {
			trace(userService.loadUsersByUserName("Tom"));
		}
	}

	@Test
	public void remove() {
		User user = new User();
		user.setId(8000001L);
		userService.removeUser(user);
	}

	@Test
	public void removeRollback() {
		// save();
		try {
			User user = new User();
			user.setId(100001L);
			user.setUsername("Tom");
			userService.removeUserAndRollback(user);
		} catch (Exception e) {
			trace(userService.loadUsersByUserName("Tom"));
		}
	}

	@Test
	public void update() {
		// save();
		User user = new User();
		user.setId(100001L);
		user.setUsername("Jim");
		userService.saveUser(user);
		user.setUsername("Sam");
		userService.updateUser(user);
	}

	@Test
	public void updateAndRollBack() {
		try {
			// save();
			User user = new User();
			user.setId(100001L);
			user.setUsername("Jim");
			userService.updateUserAndRollback(user);
		} catch (Exception e) {
			trace(userService.loadUsersByUserName("Tom"));
		}
	}

	@Test
	public void get() {
		// save();
		User user = userService.getUserById(100001L);
		trace(user);
	}

	@Test
	public void load() {
		// save();
		List<User> users = userService.loadUsersByUserName("Tom");
		trace(users);
	}

	@Test
	public void exist() {
		// save();
		boolean flag = userService.existUserName("Tom");
		_LOG.info("Flag = " + flag);
	}

	@Test
	public void abc() {
		for (int i = 0; i < 20; i++) {
			User u1 = new User();
			u1.setId(200001L + i);
			u1.setUsername("abc" + i);
			userService.saveUser(u1);
		}

		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);

		page = userService.findUserByPage(page);

		trace(page.getResult());

		page.setCurrentPage(3);
		page.setPageSize(5);

		page = userService.findUserByPage(page);

		trace(page.getResult());

	}

	/**
	 * multi- Data operations in 1 txn
	 */
	@Test
	public void mulitdo() {
		User user = new User();
		user.setId(10011L);
		user.setUsername("Tomcat");
		Book book = new Book();
		book.setId(1000001L);
		book.setBookname("java");
		userService.saveUserAndBook(user, book);
	}

	/**
	 * multi-thread operate the same data via lock
	 */
	@Test
	public void lock() {
		User user1=userService.getUserById(7000001L);
		User user2=userService.getUserById(7000001L);
		user1.setUsername("Jack");
		userService.updateUser(user1);
		user2.setUsername("Rose");
		//userService.updateUser(user2);
	}

	/**
	 * BSvc referenced in ASvc
	 */
	@Test
	public void svcref() {
		User user = new User();
		user.setId(100031L);
		user.setUsername("Tom");
		Book book1 = new Book();
		book1.setId(10000031L);
		book1.setBookname("java");
		Book book2 = new Book();
		book2.setId(10000032L);
		book2.setBookname("javaEE");
		userService.saveUserAndBook(user, book1);
		// userService.saveUserAndBooksAndRollback(user, book1, book2);
	}

	/**
	 * Annotaion txn controll
	 */
	@Test
	public void annotationtxn() {
		User user = new User();
		user.setId(10001L);
		user.setUsername("Tom");
		userService.abcUser(user);

		trace(userService.loadUsersByUserName("Tom"));

	}
	
	@Test
	public void cache(){
		User user=userService.getUserById(7000001L);
		trace(user);
		User user1=userService.getUserById(7000001L);
		trace(user1);
	}
}

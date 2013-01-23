package com.abc.bt.modules.sample.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.abc.bt.common.model.Page;
import com.abc.bt.common.test.SVCCommonTester;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;

public class SampleServiceTester extends SVCCommonTester {

	// private static final Logger _LOG =
	// Logger.getLogger(SampleServiceTester.class);

	// TODO define 2 svc: ASvc and BSvc

	@Resource(name = IUserService.SERVICE_NAME)
	private IUserService userService;

	@Resource(name = IBookService.SERVICE_NAME)
	private IBookService bookService;

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
		Assert.assertEquals("Tom", userService.getUserById(80001L).getUsername());
	}

	@Test
	public void saveRollback() {

		try {
			User user = new User();
			user.setId(100001L);
			user.setUsername("Tom");
			userService.saveUserAndRollback(user);
			Assert.fail("the saveUserAndRollback method does not throw a exception");
		} catch (Exception e) {
			Assert.assertNull(userService.getUserById(100001L));
		}
	}

	@Test
	public void remove() {
		User user = new User();
		user.setId(7000001L);
		userService.removeUser(user);
		Assert.assertNull(userService.getUserById(7000001L));
	}

	@Test
	public void removeRollback() {
		try {
			User user = new User();
			user.setId(7000002L);
			userService.removeUserAndRollback(user);
			Assert.fail("the removeRollback method does not throw a exception");
		} catch (Exception e) {
			Assert.assertNotNull(userService.getUserById(7000002L));
		}
	}

	@Test
	public void update() {
		User user = new User();
		user.setId(100002L);
		user.setUsername("Jim");
		userService.saveUser(user);
		Assert.assertEquals("Jim", userService.getUserById(100002L).getUsername());
		user.setUsername("Sam");
		userService.updateUser(user);
		Assert.assertEquals("Sam", userService.getUserById(100002L).getUsername());
	}

	@Test
	public void updateAndRollBack() {
		try {

			User user = new User();
			user.setId(100003L);
			user.setUsername("Tina");
			userService.saveUser(user);
			Assert.assertEquals("Tina", userService.getUserById(100003L).getUsername());
			user.setUsername("Jim");
			userService.updateUserAndRollback(user);
			Assert.fail("the updateUserAndRollback method does not throw a exception");
		} catch (Exception e) {
			Assert.assertEquals("Tina", userService.getUserById(100003L).getUsername());
		}
	}

	@Test
	public void get() {
		User user = userService.getUserById(7000004L);
		Assert.assertEquals("U_4", user.getUsername());
	}

	@Test
	public void load() {
		// save();
		List<User> users = userService.loadUsersByUserName("U_4");
		for (User user : users) {
			Assert.assertEquals("U_4", user.getUsername());
		}
	}

	@Test
	public void exist() {
		boolean flag = userService.existUserName("U_4");
		Assert.assertTrue(flag);
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

		Assert.assertEquals(1, page.getCurrentPage());
		Assert.assertEquals(5, page.getResult().size());

		page.setCurrentPage(3);
		page.setPageSize(5);

		page = userService.findUserByPage(page);

		Assert.assertEquals(3, page.getCurrentPage());
		Assert.assertEquals(5, page.getResult().size());

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
		book.setId(100011L);
		book.setBookname("java");
		userService.saveUserAndBook(user, book);

		Assert.assertEquals("Tomcat", userService.getUserById(10011L).getUsername());
		Assert.assertEquals("java", bookService.getBookById(100011L).getBookname());

	}

	/**
	 * multi-thread operate the same data via lock
	 */
	@Test(expected = org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException.class)
	public void lock() {
		User user1 = userService.getUserById(7000005L);
		User user2 = userService.getUserById(7000005L);
		user1.setUsername("Jack");
		userService.updateUser(user1);
		user2.setUsername("Rose");
		userService.updateUser(user2);
	}

	/**
	 * BSvc referenced in ASvc
	 */
	@Test
	public void svcref() {
		User user = new User();
		user.setId(100031L);
		user.setUsername("Tony");
		Book book1 = new Book();
		book1.setId(1000031L);
		book1.setBookname("javascript");
		userService.saveUserAndBook(user, book1);
		Assert.assertEquals("Tony", userService.getUserById(100031L).getUsername());
		Assert.assertEquals("javascript", bookService.getBookById(1000031L).getBookname());
	}

	/**
	 * BSvc referenced in ASvc
	 */
	@Test
	public void svcrefAndRollBack() {
		try {
			User user = new User();
			user.setId(100041L);
			user.setUsername("Edison");
			Book book1 = new Book();
			book1.setId(1000041L);
			book1.setBookname("java2");
			Book book2 = new Book();
			book2.setId(1000042L);
			book2.setBookname("EJB");
			userService.saveUserAndBooksAndRollback(user, book1, book2);
			Assert.fail("the saveUserAndBooksAndRollback method does not throw a exception");
		} catch (Exception e) {
			Assert.assertNull(userService.getUserById(100041L));
			Assert.assertNull(bookService.getBookById(1000041L));
			Assert.assertNull(bookService.getBookById(1000042L));
		}

		

	}

	/**
	 * Annotaion txn controll
	 */
	@Test
	public void annotationtxn() {
		User user = new User();
		user.setId(100051L);
		user.setUsername("Jimmy");
		userService.abcUser(user);
		Assert.assertEquals("Jimmy", userService.getUserById(100051L).getUsername());
	}

	@Test
	public void cache() {
		User user = userService.getUserById(7000001L);
		trace(user);
		User user1 = userService.getUserById(7000001L);
		trace(user1);
	}
}

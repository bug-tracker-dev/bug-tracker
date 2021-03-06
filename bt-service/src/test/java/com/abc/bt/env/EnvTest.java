package com.abc.bt.env;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;
import com.abc.bt.modules.sample.service.IBookService;
import com.abc.bt.modules.sample.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:conf/applicationContext-*.xml")
public class EnvTest {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Resource(name = "userService")
	private IUserService userService;

	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void test() {
		Session session = sessionFactory.openSession(); // not part of a
														// transaction, so we
														// need to open a
														// session manually

		User u = new User();
		u.setId(123213L);
		u.setUsername("abc");
		session.save(u);
		session.flush();

		Query query = session.createQuery("from User");
		List<User> a = query.list();

		session.close();
	}

	@Test
	public void testTransition() {
		try {
			User u1 = new User();
			u1.setId(123213L);
			u1.setUsername("abc");

			User u2 = new User();
			u2.setId(456233L);
			u2.setUsername("cba");
			userService.saveUser(u1);

			userService.saveUserAndRollback(u2);
		} catch (Exception e) {
			List<User> list = userService.findAll();

			System.out.println(list.size());
		}

	}

	@Test
	public void testSaveUser() {
		User u1 = new User();
		u1.setId(100001L);
		u1.setUsername("abc");
		userService.saveUser(u1);
		List<User> list = userService.findAll();
		System.out.println(list.get(0).getUsername());
	}

	@Test
	public void testUpdateUser() {
		User u1 = new User();
		u1.setId(100002L);
		u1.setUsername("abc");
		userService.saveUser(u1);
		List<User> list = userService.findAll();
		System.out.println(list.get(0).getUsername());

		u1.setUsername("efg");
		userService.updateUser(u1);
		list = userService.findAll();
		for (User user : list) {
			System.out.println("编号：" + user.getId() + "    ------     姓名：" + user.getUsername());
		}
	}

	@Test
	public void testfindUserByPage() {
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

		for (User user : page.getResult()) {
			System.out.println("编号：" + user.getId() + "    ------     姓名：" + user.getUsername());
		}

		System.out.println("---------------------------------------------------");
		page.setCurrentPage(2);

		page = userService.findUserByPage(page);

		for (User user : page.getResult()) {
			System.out.println("编号：" + user.getId() + "    ------     姓名：" + user.getUsername());
		}
	}
	
	@Resource(name="bookService")
	private IBookService bookService; 
	
	List<User> users = new ArrayList<User>();
	List<Book> books = new ArrayList<Book>();
	
	@Test
	public void insertdata() {
		User u = null;
		Book b = null;
		for (int i = 1; i < 6; i++) {
			u = new User();
			u.setId(90000 + i);
			u.setUsername("User_" + i);
			users.add(u);

			b = new Book();
			b.setId(90000 + i);
			b.setBookname("Book_" + i);
			books.add(b);
		}
		
		userService.saveUsers(users);
		bookService.saveBooks(books);
	}
	
	@Test
	public void loaddata() {
		
		List<User> users = userService.findAll();
		List<Book> books = bookService.findAll();
		
		System.out.println(JSONArray.fromObject(users));
		System.out.println(JSONArray.fromObject(books));
	}
}

package com.abc.bt.env;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.entity.User;
import com.abc.bt.modules.sample.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:conf/applicationContext-*.xml")
public class EnvTest {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Resource(name = "userService")
	private IUserService userService;

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
			userService.save(u1);

			userService.saveUserError(u2);
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
		userService.save(u1);
		List<User> list = userService.findAll();
		System.out.println(list.get(0).getUsername());
	}

	@Test
	public void testUpdateUser() {
		User u1 = new User();
		u1.setId(100002L);
		u1.setUsername("abc");
		userService.save(u1);
		List<User> list = userService.findAll();
		System.out.println(list.get(0).getUsername());

		u1.setUsername("efg");
		userService.update(u1);
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
			userService.save(u1);
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
}

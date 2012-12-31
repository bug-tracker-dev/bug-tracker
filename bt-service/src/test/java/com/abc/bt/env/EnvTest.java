package com.abc.bt.env;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bt.module.sample.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath*:conf/applicationContext-*.xml")
public class EnvTest {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void test() {
		Session session = sessionFactory.openSession(); // not part of a transaction, so we need to open a session manually
		
		User u = new User();
		u.setId(123213L);
		u.setUsername("abc");
		session.save(u);
		session.flush();
		
		Query query = session.createQuery("from User");
		List<User> a = query.list();
		
		
		session.close();
	}
}

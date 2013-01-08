package com.abc.bt.modules.sample.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.abc.bt.common.test.SVCCommonTester;
import com.abc.bt.modules.sample.entity.User;

public class SampleServiceTester extends SVCCommonTester {

	// TODO define 2 svc: ASvc and BSvc
	
	@Resource(name="userService")
	private IUserService userService; 
	
	private static final Logger _LOG = Logger.getLogger(SampleServiceTester.class);

	
	private void showUser(User user) {
		if(null!=user){
			_LOG.info("id:" + user.getId() + "  username:" + user.getUsername());
		}
	}

	private void showUsers(Collection<User> users) {
		for (User user : users) {
			showUser(user);
		}
	}

	
	// TODO 
	
	/**
	 * test the transaction propagations and rollbacks
	 * 7 cases need to be tested and 3 rollbacks
	 * 	<tx:method name="save*" propagation="REQUIRED" rollback-for="Exception"/>
		<tx:method name="remove*" propagation="REQUIRED" rollback-for="Exception" />
		<tx:method name="update*" propagation="REQUIRED" rollback-for="Exception"  />
		<tx:method name="get*" propagation="REQUIRED" />
		<tx:method name="load*" propagation="REQUIRED" />
		<tx:method name="exist*" propagation="REQUIRED" />
		<tx:method name="*" read-only="true" />
	 */
	@Test
	public void save() {
		User user=new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userService.saveUser(user);
	}
	
	@Test
	public void saveRollback() {
		User user=new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userService.saveUserAndRollback(user);
	}
	
	@Test
	public void remove() {
		save();
		User user=new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userService.removeUser(user);
	}
	
	@Test
	public void removeRollback() {
		save();
		User user=new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userService.removeUserAndRollback(user);
	}
	
	
	
	/**
	 * multi- Data operations in 1 txn
	 */
	@Test public void mulitdo() {}
	
	/**
	 * multi-thread operate the same data via lock
	 */
	@Test public void lock() {}
	
	/**
	 * BSvc referenced in ASvc
	 */
	@Test public void svcref() {}
	
	/**
	 * Annotaion txn controll
	 */
	@Test public void annotationtxn() {}
}

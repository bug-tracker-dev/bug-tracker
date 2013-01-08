package com.abc.bt.common.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abc.bt.common.model.Page;
import com.abc.bt.common.test.SVCCommonTester;
import com.abc.bt.modules.sample.dao.IUserDao;
import com.abc.bt.modules.sample.entity.User;

/**
 * the cases in tester should cover all the methods defined in class
 * GenericDaoHibernateSupport
 * 
 * @see com.abc.bt.common.dao.GenericDaoHibernateSupport
 * 
 * zhaosen
 */
@Transactional(readOnly=true)
public class GenericDaoHibernateSupportTester extends SVCCommonTester {

	@Resource(name = "userDao")
	private IUserDao userDao;

	private static final Logger _LOG = Logger.getLogger(GenericDaoHibernateSupportTester.class);

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

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save() {
		User user = new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userDao.save(user);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Test
	public void findAll() {
		List<User> users = userDao.findAll();
		showUsers(users);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void delete() {
		User user = new User();
		user.setId(100001L);
		user.setUsername("Tom");
		userDao.delete(user);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void update() {
		User user = new User();
		user.setId(100001L);
		user.setUsername("Jack");
		userDao.update(user);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteById() {
		save();
		userDao.deleteById(100001L);
	}

	@Test
	public void excuteQuery() {
		try {
			ResultSet rs = userDao.executeQuery("select id,username from T_User");
			while (rs.next()) {
				_LOG.info("id:" + rs.getObject("id") + "   " + "username:" + rs.getObject("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findAllByProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 10001L);
		map.put("username", "Tom");
		List<User> users = userDao.findAllByProperties(map);
		showUsers(users);
	}

	@Test
	public void findAllByProperty() {
		List<User> users = userDao.findAllByProperty("username", "Tom");
		showUsers(users);
	}

	@Test
	public void findByHQL() {
		List<User> users = userDao.findByHQL("from User");
		showUsers(users);
	}

	@Test
	public void findById() {
		User user = userDao.findById(10001L);
		showUser(user);
	}

	@Test
	public void findColumnCount() {
		int count = userDao.findColumnCount("username", "Tom");
		_LOG.info("COUNT:" + count);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveAll() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(10001L + i);
			user.setUsername("Jim" + i);
			users.add(user);
		}
		userDao.saveAll(users);
	}

	@Test
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteAll() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(10001L + i);
			user.setUsername("Jim" + i);
			users.add(user);
		}
		userDao.deleteAll(users);
	}

	@Test
	public void selectBySqlCondition() {
		List<User> users = userDao.selectBySqlCondition("id::=10001", "username::='Tom'");
		showUsers(users);
	}

	@Test
	public void selectBySqlConditionOr() {
		List<User> users = userDao.selectBySqlConditionOr("username::='Tom'", "username::='Jim0'");
		showUsers(users);
	}
	
	@Test
	public void pageQuery(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		page = userDao.pageQuery(page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());
	}
	
	@Test
	public void pageQueryOverLoad1(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "tom");
		page = userDao.pageQuery(conditionMap, page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
	}
	
	@Test
	public void pageQueryOverLoad2(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		LinkedHashMap<String,String> orderBy=new LinkedHashMap<String,String>();
		orderBy.put("id", "desc");
		page = userDao.pageQuery(page, orderBy);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
	}
	
	@Test
	public void pageQueryOverLoad3(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "tom");
		page = userDao.pageQuery(conditionMap, page, orderby);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
	}
	
	@Test
	public void pageQueryOverLoad4(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "tom");
		page = userDao.pageQuery(conditionMap, page, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
	}
	
	@Test
	public void pageQueryOverLoad5(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "tom");
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		page = userDao.pageQuery(conditionMap, page, orderby, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
	}

}

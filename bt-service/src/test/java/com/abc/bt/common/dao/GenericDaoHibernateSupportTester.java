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
import org.junit.Assert;
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
 */
@Transactional(readOnly=true)
public class GenericDaoHibernateSupportTester extends SVCCommonTester {

	@Resource(name = IUserDao.DAO_NAME)
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
		Assert.assertEquals(5, users.size());
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
		userDao.deleteById(7000001L);
	}

	@Test
	public void excuteQuery() {
		try {
			ResultSet rs = userDao.executeQuery("select id,username from T_User");
			int i=0;
			while (rs.next()) {
				_LOG.info("id:" + rs.getObject("id") + "   " + "username:" + rs.getObject("username"));
				i++;
			}
			Assert.assertEquals(5, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findAllByProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 7000001L);
		map.put("username", "U_1");
		List<User> users = userDao.findAllByProperties(map);
		showUsers(users);
		Assert.assertEquals(1, users.size());
	}

	@Test
	public void findAllByProperty() {
		List<User> users = userDao.findAllByProperty("username", "U_1");
		showUsers(users);
		Assert.assertEquals(1, users.size());
	}

	@Test
	public void findByHQL() {
		List<User> users = userDao.findByHQL("from User");
		showUsers(users);
		Assert.assertEquals(5, users.size());
	}

	@Test
	public void findById() {
		User user = userDao.findById(7000001L);
		showUser(user);
		Assert.assertEquals("U_1", user.getUsername());
	}

	@Test
	public void findColumnCount() {
		int count = userDao.findColumnCount("username", "U_2");
		_LOG.info("COUNT:" + count);
		Assert.assertEquals(1, count);
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
		List<User> users = userDao.selectBySqlCondition("id::=7000003", "username::='U_3'");
		showUsers(users);
		Assert.assertEquals(1,users.size());
	}

	@Test
	public void selectBySqlConditionOr() {
		List<User> users = userDao.selectBySqlConditionOr("id::=7000002", "username::='U_1'");
		showUsers(users);
		Assert.assertEquals(2,users.size());
	}
	
	@Test
	public void pageQuery(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		page = userDao.pageQuery(page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(5,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
	}
	
	@Test
	public void pageQueryOverLoad1(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U_1");
		page = userDao.pageQuery(conditionMap, page);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
		
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(1,page.getTotalCount());
		Assert.assertEquals(5,page.getPageSize());
		Assert.assertEquals(1,page.getResult().size());
	
	}
	
	@Test
	public void pageQueryOverLoad2(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		LinkedHashMap<String,String> orderBy=new LinkedHashMap<String,String>();
		orderBy.put("id", "desc");
		page = userDao.pageQuery(page, orderBy);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
		List<User> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(5,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	public void pageQueryOverLoad3(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U_1");
		page = userDao.pageQuery(conditionMap, page, orderby);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
		
		List<User> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(1,page.getTotalCount());
		Assert.assertEquals(5,page.getPageSize());
		Assert.assertEquals(1,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	public void pageQueryOverLoad4(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U");
		page = userDao.pageQuery(conditionMap, page, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
		
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(5,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
	}
	
	@Test
	public void pageQueryOverLoad5(){
		Page<User> page = new Page<User>();
		page.setCurrentPage(1);
		page.setPageSize(3);
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("username", "U");
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("id", "desc");
		page = userDao.pageQuery(conditionMap, page, orderby, true);
		_LOG.info("current page:"+page.getCurrentPage()+"   page size:"+page.getPageSize());
		showUsers(page.getResult());	
		List<User> users=page.getResult();
		Assert.assertEquals(1,page.getCurrentPage());
		Assert.assertEquals(5,page.getTotalCount());
		Assert.assertEquals(3,page.getPageSize());
		Assert.assertEquals(3,page.getResult().size());
		
		for(int i=0;i<page.getResult().size()-1;i++){
			Assert.assertTrue(users.get(i).getId()>users.get(i+1).getId());
		}
	}
	
	@Test
	public void loadById(){
		User user=userDao.loadById(7000001L);
		showUser(user);
		Assert.assertEquals("U_1", user.getUsername());		
	}

}

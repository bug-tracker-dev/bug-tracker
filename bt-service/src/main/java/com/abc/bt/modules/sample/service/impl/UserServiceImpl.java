package com.abc.bt.modules.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.dao.IUserDao;
import com.abc.bt.modules.sample.entity.User;
import com.abc.bt.modules.sample.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void saveUserAndRollback(User user) {
		userDao.save(user);
		throw new RuntimeException("回滚吧!");
		// int i=1/0;
	}

	public void saveUser(User user) {
		userDao.save(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}
	
	public void updateUserAndRollback(User user){
		userDao.update(user);
		throw new RuntimeException("回滚吧!");
	}

	public void removeUser(User user){
		userDao.delete(user);
	}
	
	public void removeUserAndRollback(User user){
		userDao.delete(user);
		throw new RuntimeException("回滚吧!");
	}
	
	public User getUserByID(Long id){
		return userDao.findById(id);
	}
	
	public List<User> loadUsersByUserName(String username){
		return userDao.findAllByProperty("username", username);
	}
	
	public Page<User> findUserByPage(Page<User> page) {
		return userDao.pageQuery(page);
	}
}

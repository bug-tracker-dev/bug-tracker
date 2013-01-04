package com.abc.bt.modules.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.dao.IUserDao;
import com.abc.bt.modules.sample.model.User;
import com.abc.bt.modules.sample.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}

	public void saveUserError(User user){
		userDao.save(user);
		int i=1/0;
	}
	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user){
		userDao.update(user);
	}
	
	public Page<User> findUserByPage(Page<User> page){
		return userDao.pageQuery(page);
	}
}

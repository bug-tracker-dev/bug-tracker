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

	public void saveUserError(User user) {
		userDao.save(user);
		throw new RuntimeException("出错了");
		// int i=1/0;
	}

	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public Page<User> findUserByPage(Page<User> page) {
		return userDao.pageQuery(page);
	}
}

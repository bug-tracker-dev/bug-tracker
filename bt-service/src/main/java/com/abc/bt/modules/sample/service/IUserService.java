package com.abc.bt.modules.sample.service;

import java.util.List;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.entity.User;

public interface IUserService {

	/**
	 * 查找所有用户列表
	 * 
	 * @return
	 */
	List<User> findAll();

	void save(User user);

	public void saveUserError(User user);

	public void update(User user);

	public Page<User> findUserByPage(Page<User> page);
}

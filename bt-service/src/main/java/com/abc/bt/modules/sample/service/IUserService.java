package com.abc.bt.modules.sample.service;

import java.util.List;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;

public interface IUserService {

	/**
	 * 查找所有用户列表
	 * 
	 * @return
	 */
	List<User> findAll();

	void saveUser(User user);

	public void saveUserAndRollback(User user);

	public void updateUser(User user);
	
	public void updateUserAndRollback(User user);
	
	public void removeUser(User user);
	
	public void removeUserAndRollback(User user);
	
	public User getUserByID(Long id);

	public List<User> loadUsersByUserName(String username);
	
	public boolean existUserName(String username);
	
	public Page<User> findUserByPage(Page<User> page);
	
	public void saveUserAndBooksAndRollback(User user,Book book1, Book book2);
	
	public void saveUserAndBook(User user,Book book);
	
	public void insertUser(User user);
}

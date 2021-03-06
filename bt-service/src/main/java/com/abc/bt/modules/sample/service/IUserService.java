package com.abc.bt.modules.sample.service;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;

public interface IUserService {
	
	public final static String  SERVICE_NAME="userService";

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
	
	public User getUserById(Long id);

	public List<User> loadUsersByUserName(String username);
	
	public boolean existUserName(String username);
	
	public Page<User> findUserByPage(Page<User> page);
	
	public void saveUserAndBooksAndRollback(User user,Book book1, Book book2);
	
	public void saveUserAndBook(User user,Book book);
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = RuntimeException.class, rollbackFor=Exception.class)
	public void abcUser(User user);
	
	public void saveUsers(Collection<User> users);

	//public User loadUserById(Long id);
	
}

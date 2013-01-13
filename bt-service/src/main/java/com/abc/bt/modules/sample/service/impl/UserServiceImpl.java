package com.abc.bt.modules.sample.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.abc.bt.common.model.Page;
import com.abc.bt.modules.sample.dao.IUserDao;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.entity.User;
import com.abc.bt.modules.sample.service.IBookService;
import com.abc.bt.modules.sample.service.IUserService;

@Service(value = IUserService.SERVICE_NAME)
public class UserServiceImpl implements IUserService {

	@Resource(name = IUserDao.DAO_NAME)
	private IUserDao userDao;
	
	@Resource(name = IBookService.SERVICE_NAME)
	private IBookService bookService;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void saveUserAndRollback(User user) {
		userDao.save(user);
		throw new RuntimeException("回滚吧!");
		// int i=1/0;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	@Override
	public void updateUserAndRollback(User user){
		userDao.update(user);
		throw new RuntimeException("回滚吧!");
	}

	@Override
	public void removeUser(User user){
		userDao.delete(user);
	}
	
	@Override
	public void removeUserAndRollback(User user){
		userDao.delete(user);
		throw new RuntimeException("回滚吧!");
	}
	
	@Override
	public User getUserById(Long id){
		return userDao.findById(id);
	}
	
	@Override
	public List<User> loadUsersByUserName(String username){
		return userDao.findAllByProperty("username", username);
	}
	
	@Override
	public Page<User> findUserByPage(Page<User> page) {
		return userDao.pageQuery(page);
	}

	@Override
	public boolean existUserName(String username) {
		List<User> users=userDao.findAllByProperty("username", username);
		if(null==users||users.size()<1){
			return false;
		}
		return true;
	}

	@Override
	public void saveUserAndBooksAndRollback(User user,Book book1, Book book2) {
		userDao.save(user);
		bookService.saveBook(book1);
		bookService.saveBookAndRollBack(book2);
	}
	
	@Override
	public void saveUserAndBook(User user,Book book) {
		userDao.save(user);
		bookService.saveBook(book);
	}

	@Override
	public void abcUser(User user) {
		userDao.save(user);
	}

	@Override
	public void saveUsers(Collection<User> users) {
		userDao.saveAll(users);
	}

	/*@Override
	public User loadUserById(Long id) {
		return userDao.loadById(id);
	}*/

}

package com.abc.bt.modules.sample.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.abc.bt.modules.sample.dao.IBookDao;
import com.abc.bt.modules.sample.entity.Book;
import com.abc.bt.modules.sample.service.IBookService;

@Service(value="bookService")
public class BookServiceImpl implements IBookService{

	@Resource(name="bookDao")
	private IBookDao bookDao;
	
	@Override
	public void saveBook(Book book) {
		bookDao.save(book);
	}
	
	@Override
	public void saveBookAndRollBack(Book book) {
		bookDao.save(book);
		throw new RuntimeException("回滚吧！");
	}

	@Override
	public void saveBooks(Collection<Book> books) {
		bookDao.saveAll(books);
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}

}

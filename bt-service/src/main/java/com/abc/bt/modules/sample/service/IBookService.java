package com.abc.bt.modules.sample.service;

import java.util.Collection;
import java.util.List;

import com.abc.bt.modules.sample.entity.Book;

public interface IBookService {

	void saveBook(Book book);
	
	void saveBookAndRollBack(Book book);
	
	void saveBooks(Collection<Book> books);
	
	public List<Book> findAll();
}

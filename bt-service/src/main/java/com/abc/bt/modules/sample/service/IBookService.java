package com.abc.bt.modules.sample.service;

import java.util.Collection;
import java.util.List;

import com.abc.bt.modules.sample.entity.Book;

public interface IBookService {
	
	public final static String SERVICE_NAME="bookService";

	void saveBook(Book book);
	
	void saveBookAndRollBack(Book book);
	
	void saveBooks(Collection<Book> books);
	
	public List<Book> findAll();
	
	public Book getBookById(Long id);
}

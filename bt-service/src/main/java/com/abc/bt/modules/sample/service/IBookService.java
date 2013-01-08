package com.abc.bt.modules.sample.service;

import com.abc.bt.modules.sample.entity.Book;

public interface IBookService {

	void saveBook(Book book);
	
	void saveBookAndRollBack(Book book);
}

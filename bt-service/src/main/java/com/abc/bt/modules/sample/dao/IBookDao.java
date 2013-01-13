package com.abc.bt.modules.sample.dao;

import com.abc.bt.common.dao.GenericDao;
import com.abc.bt.modules.sample.entity.Book;

public interface IBookDao extends GenericDao<Book> {
	
	public final static String DAO_NAME="bookDao";
	
}

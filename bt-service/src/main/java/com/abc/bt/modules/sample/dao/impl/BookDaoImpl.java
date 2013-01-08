package com.abc.bt.modules.sample.dao.impl;

import org.springframework.stereotype.Repository;

import com.abc.bt.common.dao.GenericDaoHibernateSupport;
import com.abc.bt.modules.sample.dao.IBookDao;
import com.abc.bt.modules.sample.entity.Book;

@Repository(value = "bookDao")
public class BookDaoImpl extends GenericDaoHibernateSupport<Book> implements IBookDao {

}

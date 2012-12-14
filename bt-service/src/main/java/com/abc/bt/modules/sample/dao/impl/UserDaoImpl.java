package com.abc.bt.module.sample.dao.impl;

import org.springframework.stereotype.Component;

import com.abc.bt.common.dao.GenericDaoHibernateSupport;
import com.abc.bt.module.sample.dao.IUserDao;
import com.abc.bt.module.sample.model.User;

@Component("userDao")
public class UserDaoImpl extends GenericDaoHibernateSupport<User> implements
		IUserDao {

	

}

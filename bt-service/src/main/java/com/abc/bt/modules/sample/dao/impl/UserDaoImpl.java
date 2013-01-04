package com.abc.bt.modules.sample.dao.impl;

import org.springframework.stereotype.Repository;

import com.abc.bt.common.dao.GenericDaoHibernateSupport;
import com.abc.bt.modules.sample.dao.IUserDao;
import com.abc.bt.modules.sample.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoHibernateSupport<User> implements
		IUserDao {

	

}

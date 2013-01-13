package com.abc.bt.modules.sample.dao;

import com.abc.bt.common.dao.GenericDao;
import com.abc.bt.modules.sample.entity.User;

public interface IUserDao extends GenericDao<User> {
	
	public final static String DAO_NAME="userDao";
	
}

package com.hxzy.store.dao;

import java.sql.SQLException;

import com.hxzy.store.domain.User;

public interface UserDao {
	
	public void userRigist(User user) throws SQLException;
	
	public User userActive(String code)  throws SQLException;
	
	public void updateUser(User user)throws SQLException;
	
	public User userLogin(User user) throws SQLException;

}

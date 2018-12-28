package com.hxzy.store.service;

import java.sql.SQLException;

import com.hxzy.store.domain.User;

public interface UserService {
	
	public void userRigist(User user) throws SQLException;
	
	public boolean userActive(String code)  throws SQLException;
	
	public User userLogin(User user) throws SQLException;
}

package com.hxzy.store.serviceimpl;

import java.sql.SQLException;

import com.hxzy.store.daoimpl.UserDaoImpl;
import com.hxzy.store.domain.User;
import com.hxzy.store.service.UserService;

public class UserServiceImpl  implements UserService{
	UserDaoImpl userDao=  new  UserDaoImpl();
	@Override
	public void userRigist(User user) throws SQLException {
		userDao.userRigist(user);
		
	}
	@Override
	public boolean userActive(String code)  throws SQLException {
		//code查询
		User user=userDao.userActive(code);
		//有这个用户
		if(null!=user){
			//修改用户激活
		    user.setState(1);
			user.setCode(null);
			userDao.updateUser(user);
			return true;
		}else{
			
			return false;
		}
	
	}
	@Override
	public User userLogin(User user) throws SQLException {
		User u=userDao.userLogin(user);
		if(null==u){
			throw new RuntimeException("用户名或密码有误");
			
		}else if(u.getState()==0){
			throw new RuntimeException("用户未激活");
			
		}else{
			return u;
		}
	
	}

	

}

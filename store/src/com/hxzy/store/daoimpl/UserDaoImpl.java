package com.hxzy.store.daoimpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hxzy.store.dao.UserDao;
import com.hxzy.store.domain.User;
import com.hxzy.store.util.JDBCUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void userRigist(User user) throws SQLException {
		//dbutils
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		//插入数据库
		Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		
		qr.update("insert into user values(?,?,?,?,?,?,?,?,?,?)", params);
	
		
	}

	@Override
	public User userActive(String code) throws SQLException {
		//dbutils
	    QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	    User user=qr.query("select * from user where code=?", new BeanHandler<User>(User.class), code);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		//dbutils
	    QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	    qr.update("update user set state=?, code=? where uid=?", user.getState(),user.getCode(),user.getUid());
	    //  qr.update("update user set state=? and code=? where uid=?", user.getState(),user.getCode(),user.getUid());
		
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//dbutils
	    QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

}

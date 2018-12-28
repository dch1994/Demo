package com.hxzy.store.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hxzy.store.dao.CategoryDao;
import com.hxzy.store.domain.Category;
import com.hxzy.store.util.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> getAllCats() throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from category", new BeanListHandler<Category>(Category.class));
	}

}

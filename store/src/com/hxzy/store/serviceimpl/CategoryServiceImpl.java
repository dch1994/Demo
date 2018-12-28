package com.hxzy.store.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.daoimpl.CategoryDaoImpl;
import com.hxzy.store.domain.Category;
import com.hxzy.store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	public CategoryDaoImpl cd=new CategoryDaoImpl();

	@Override
	public List<Category> getAllCats() throws SQLException{
	
		return cd.getAllCats();
	}

}

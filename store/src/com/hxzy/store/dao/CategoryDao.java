package com.hxzy.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.domain.Category;

public interface CategoryDao {

	
	 public List<Category>  getAllCats() throws SQLException;
}

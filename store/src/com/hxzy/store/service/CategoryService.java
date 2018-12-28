package com.hxzy.store.service;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.domain.Category;

public interface CategoryService {
	
    public List<Category>  getAllCats() throws SQLException;
}

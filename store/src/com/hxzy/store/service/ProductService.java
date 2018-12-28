package com.hxzy.store.service;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.Product;

public interface ProductService {
	
	public List<Product> findHots() throws SQLException ;
	
	public Product findProductByPid(String pid) throws SQLException ;
	
	public PageModel findProductByIdWithPage(String cid,int curnum) throws SQLException ;

}

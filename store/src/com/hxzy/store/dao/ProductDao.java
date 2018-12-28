package com.hxzy.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.domain.Product;

public interface ProductDao {
	public List<Product> findHots() throws SQLException ;
	public Product findProductByPid(String pid) throws SQLException ;
	public int findTotalRecords(String cid)throws SQLException ;
	
	public List findProductByIdWithPage(String cid,int startIndex,int pageSize)throws SQLException ;
	
}

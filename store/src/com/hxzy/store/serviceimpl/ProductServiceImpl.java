package com.hxzy.store.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.daoimpl.ProductDaoImpl;
import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.Product;
import com.hxzy.store.service.ProductService;

public class ProductServiceImpl implements ProductService{
	ProductDaoImpl productDaoImpl= new ProductDaoImpl();
	@Override
	public List<Product> findHots() throws SQLException {
	
		return productDaoImpl.findHots();
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		
		return productDaoImpl.findProductByPid(pid);
	}

	@Override
	public PageModel findProductByIdWithPage(String cid, int curnum) throws SQLException {
		//查询总共多少条数据
		int totalRecords=productDaoImpl.findTotalRecords(cid);
		//pagemodel计算 各个值
		PageModel pm=new PageModel(curnum,totalRecords,12);
		List list=productDaoImpl.findProductByIdWithPage(cid, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductByIdWithPage&cid="+cid);
		return pm;
	}

}

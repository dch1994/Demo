package com.hxzy.store.web.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.Product;
import com.hxzy.store.serviceimpl.ProductServiceImpl;
import com.hxzy.store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	
	public String execute(HttpServletRequest request,HttpServletResponse response ) throws SQLException{
		//查询热门商品
		ProductServiceImpl ps=new ProductServiceImpl();
		List<Product> list=ps.findHots();
		//request session
		request.setAttribute("list1", list);
		return "/jsp/index.jsp";
		
	}

}

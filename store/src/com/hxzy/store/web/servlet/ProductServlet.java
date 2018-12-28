package com.hxzy.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.Product;
import com.hxzy.store.serviceimpl.ProductServiceImpl;
import com.hxzy.store.web.base.BaseServlet;


public class ProductServlet extends BaseServlet {

	
	
	public String findProductByPid(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
	    
	    //获取pid
		String pid=request.getParameter("pid");
		//业务层
		ProductServiceImpl p=new ProductServiceImpl();
		Product m=p.findProductByPid(pid);
		//带数据
		request.setAttribute("p1", m);
		return "/jsp/product_info.jsp";
	}
	
	
	public String findProductByIdWithPage(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		int  curnum=Integer.parseInt(request.getParameter("num"));//1
		String cid1=request.getParameter("cid");
		
		ProductServiceImpl ps=new ProductServiceImpl();
		PageModel pm=ps.findProductByIdWithPage(cid1, curnum);
		request.setAttribute("page", pm);
		return "/jsp/product_list.jsp";
		
	}
}


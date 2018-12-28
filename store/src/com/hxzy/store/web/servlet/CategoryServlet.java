package com.hxzy.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.Category;
import com.hxzy.store.serviceimpl.CategoryServiceImpl;
import com.hxzy.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

public class CategoryServlet extends BaseServlet {

	
	
	public String findAllCats(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//调用service方法进行查询
		CategoryServiceImpl qs=new CategoryServiceImpl();
		String jsonStr=null;
		try {
			List<Category>  list=qs.getAllCats();
			//把list转化json格式的字符串
			jsonStr=JSONArray.fromObject(list).toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonStr);
		//return jsonStr;
		return null;
	}
	
	

	
}

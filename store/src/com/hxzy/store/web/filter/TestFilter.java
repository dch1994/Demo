package com.hxzy.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hxzy.store.domain.User;

public class TestFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest rs=(HttpServletRequest)request;
	     HttpSession s=	rs.getSession();
	     User user=(User)  s.getAttribute("user1");
	     if(null!=user){
	    	 chain.doFilter(request, response);
	     }else{
	    	 
	    	 rs.setAttribute("user1", user);
	    	 rs.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	     }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}

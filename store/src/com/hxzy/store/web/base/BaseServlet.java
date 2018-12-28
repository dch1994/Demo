package com.hxzy.store.web.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //http://localhost:8081/store/userServlet?method=addUser
	   //http://localhost:8081/store/userServlet?method=loginUser
		//findAllCats
		String method= request.getParameter("method");
		//addUser
	    if("".equals(method)||null==method||method.trim().equals("")){
	    	method="execute";
	    }
	  // 获取子类的 类对象
	  Class clazz= this.getClass(); 
	  try {
		  //返回一个方法对象 method =addUser
		  Method m= clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
		  //反射调用方法
		  if(null!=m){                       //execute
			String  jsppath=(String) m.invoke(this, request,response);
			// /jsp/add.jsp
		     if(jsppath!=null){  //          /jsp/regist.jsp
		    	 request.getRequestDispatcher(jsppath).forward(request, response);
		    
		     }
		  }
		
		} catch (Exception e) {
		 e.printStackTrace();
		}

     }

	
	
}
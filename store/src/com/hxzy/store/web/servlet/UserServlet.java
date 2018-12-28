package com.hxzy.store.web.servlet;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.User;
import com.hxzy.store.serviceimpl.UserServiceImpl;
import com.hxzy.store.util.MailUtils;
import com.hxzy.store.util.MyBeanUtils;
import com.hxzy.store.util.UUIDUtils;
import com.hxzy.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	
	public String registUI(HttpServletRequest request,HttpServletResponse response){
		 return "/jsp/register.jsp";
		
	}
	

	public String  loginUI(HttpServletRequest request,HttpServletResponse response){
		
		 return "/jsp/login.jsp";
		
	}
	
	public String userRegist(HttpServletRequest request,HttpServletResponse response) throws AddressException, MessagingException{
		
		Map<String,String[]> map=request.getParameterMap();
		User u=new User();
		MyBeanUtils.populate(u, map);
	    u.setUid(UUIDUtils.getId());
		u.setState(0);
		u.setCode(UUIDUtils.getCode());
		//插入数据库
		UserServiceImpl userService=new UserServiceImpl();
		try {
			userService.userRigist(u);
			//发邮件
			MailUtils.sendMail(u.getEmail(), u.getCode());
			
			request.setAttribute("msg", "用户注册成功，请激活");
		} catch (SQLException e) {
		
			e.printStackTrace();
			request.setAttribute("msg", "用户注册失败，请重新注册");
		}
		return "/jsp/info.jsp";
	}

	//http://localhost:8080/store/UserServlet?method=active&code=AC000AA2A04C4AFA95C1BC20867A44B6
	public String  active(HttpServletRequest request,HttpServletResponse response) throws SQLException{
	   	 String code=request.getParameter("code");
	   	 //调用业务层的方法
	     UserServiceImpl userService=new UserServiceImpl();
	     boolean flag=	userService.userActive(code);
	     if(flag==true){
	    	request.setAttribute("msg", "用户激活成功，请登陆");
	    	return "/jsp/login.jsp";
	    	 
	     }else{
	    	 request.setAttribute("msg", "用户激活失败，请重新激活");
	    	 return "/jsp/info.jsp";
	    	 
	     }
		 
		
	}
	public String  userLogin(HttpServletRequest request,HttpServletResponse response) {
		User user= new User();
		Map<String,String[]> map=request.getParameterMap();
		
		MyBeanUtils.populate(user, map);
		//调用业务层的登陆方法
	    UserServiceImpl userServiceImpl=	new UserServiceImpl();
	    try {
			User us=userServiceImpl.userLogin(user);
			request.getSession().setAttribute("user", us);
			response.sendRedirect("/store/jsp/index.jsp");
			return null;
		} catch (Exception e) {
		  String msg= e.getMessage();
		  System.out.println(msg);
		  request.setAttribute("msg", msg);
		  return "/jsp/login.jsp";
		}
		
		
	}
	
	
}

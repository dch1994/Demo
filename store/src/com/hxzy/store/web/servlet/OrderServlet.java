package com.hxzy.store.web.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.Cart;
import com.hxzy.store.domain.CartItem;
import com.hxzy.store.domain.Order;
import com.hxzy.store.domain.OrderItem;
import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.User;
import com.hxzy.store.serviceimpl.OrderServiceImpl;
import com.hxzy.store.util.UUIDUtils;
import com.hxzy.store.web.base.BaseServlet;

public class OrderServlet extends BaseServlet {
	
	public String savaOrder(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取user
		User user=(User)request.getSession().getAttribute("user");
		if(null==user){
			request.setAttribute("msg", "请登陆");
			return  "/jsp/info.jsp";	
		}
		//从购物车拿数据
		Cart cart=(Cart)request.getSession().getAttribute("cart1");
		Order order=new Order();
		order.setOid(UUIDUtils.getId());
		order.setTotal(cart.getTotal());
		order.setOrdertime(new Date());
		order.setState(1);
		order.setUser(user);
		List list1=new ArrayList();
		//获取订单项
		for(CartItem c:cart.getCartItems()){
			OrderItem o=new OrderItem();
			o.setItemid(UUIDUtils.getId());
			o.setQuantity(c.getNum());
			o.setTotal(c.getSubTotal());
			o.setProduct(c.getProduct());
			o.setOrder(order);
			list1.add(o);
			
		}
		order.setList(list1);
		//调用业务层的方法
		OrderServiceImpl os=new OrderServiceImpl();
		os.savaOrder(order);
		//order
		request.setAttribute("order1", order);
		//清空购物车
		cart.clearCart();
		return "/jsp/order_info.jsp";
	}
	
	public String confirmOrder(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    String address=  request.getParameter("address");
	    String name=  request.getParameter("name");
	    String telephone=  request.getParameter("telephone"); 
		String id=request.getParameter("id");
		//调用业务方法 查询
		OrderServiceImpl os=new OrderServiceImpl();
	    Order order=os.findOrderByOid(id);
	    order.setAddress(address);
	    order.setName(name);
	    order.setTelephone(telephone);
	    order.setState(2);
	    os.updateOrder(order);
	    
	    request.setAttribute("msg", "恭喜你，下单成功");
		return "/jsp/info.jsp";
		
	}
	
	public String findMyOrderByUid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//user
		User user=(User)request.getSession().getAttribute("user");
		//获取当前页
		int num=Integer.parseInt(request.getParameter("num"));
		OrderServiceImpl os=new OrderServiceImpl();
		PageModel pm=os.findMyOrderByUid(user,num);
		request.setAttribute("page", pm);
		return "/jsp/order_list.jsp";
	}
	
}

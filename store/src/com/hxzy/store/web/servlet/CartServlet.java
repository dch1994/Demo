package com.hxzy.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hxzy.store.domain.Cart;
import com.hxzy.store.domain.CartItem;
import com.hxzy.store.domain.Product;
import com.hxzy.store.serviceimpl.ProductServiceImpl;
import com.hxzy.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet{
	
	public String addCartItemToCart(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
		//从session获取购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart1");
		if(null==cart){
			//创建购物车对象
			cart=new Cart();
			request.getSession().setAttribute("cart1", cart);
		}
		//获取商品的pid
		String id=request.getParameter("pid");
		String num=request.getParameter("quantity");
		ProductServiceImpl productServiceImpl=new ProductServiceImpl();
		Product p=	productServiceImpl.findProductByPid(id);
		CartItem cartItem=new CartItem();
		
		cartItem.setNum(Integer.parseInt(num));
		cartItem.setProduct(p);
		
		cart.addCartItemToCart(cartItem);
		//跳转 重定向
		response.sendRedirect("/store/jsp/cart.jsp");
		
		//把购物项加到购物车
		return null;
	}
	
	
    public String removeCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String pid= request.getParameter("id");
		//获取cart
        Cart c=(Cart)request.getSession().getAttribute("cart1");
        c.removeCartItemByPid(pid);
    	
    	//跳转 重定向
		response.sendRedirect("/store/jsp/cart.jsp");
		
		//把购物项加到购物车
		return null;
			   
	}


}

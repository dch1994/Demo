package com.hxzy.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private double total=0;
	private Map<String,CartItem> map=new HashMap<String,CartItem>();

	public void addCartItemToCart(CartItem cartItem){
		//获取商品的id
		String pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem old= map.get(pid);
			old.setNum(old.getNum()+cartItem.getNum());
			
		}else{
			
			map.put(pid, cartItem);
		}
	}
	
	public Collection<CartItem> getCartItems(){
		 return map.values();
		
	}
	public  double getTotal(){
		total=0;
		Collection<CartItem> items=	map.values();
		for(CartItem c:items){
			total+=c.getSubTotal();
			
		}
		return total;
	}

	public void removeCartItemByPid(String pid) {
		map.remove(pid);
	}
	
	
	public void clearCart(){
		map.clear();	
	}
	
}

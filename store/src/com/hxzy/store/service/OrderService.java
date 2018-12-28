package com.hxzy.store.service;

import java.sql.SQLException;

import com.hxzy.store.domain.Order;
import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.User;

public interface OrderService {
	
	public void savaOrder(Order order) throws Exception;
	
	public Order findOrderByOid(String id)throws Exception;
	
    public void updateOrder(Order order)throws Exception;
    
    public PageModel findMyOrderByUid(User user,int num)throws Exception;
}

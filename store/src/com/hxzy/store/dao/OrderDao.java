package com.hxzy.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.domain.Order;
import com.hxzy.store.domain.OrderItem;
import com.hxzy.store.domain.User;

public interface OrderDao {
	public void saveOrder(Connection conn,Order order) throws Exception;
	public void saveOrderItem(Connection conn,OrderItem orderItem) throws Exception;
    public Order findOrderByOid(String id)throws Exception;
    
    public void updateOrder(Order order)throws Exception;
    
    public int getTotalRecords(Connection conn,User user) throws Exception;
    
    public List<Order> findMyOrderByUid(Connection conn,User user,int startIndex,int pageSize) throws Exception;

}

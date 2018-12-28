package com.hxzy.store.serviceimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hxzy.store.daoimpl.OrderDaoImpl;
import com.hxzy.store.domain.Order;
import com.hxzy.store.domain.OrderItem;
import com.hxzy.store.domain.PageModel;
import com.hxzy.store.domain.User;
import com.hxzy.store.service.OrderService;
import com.hxzy.store.util.JDBCUtils;

public class OrderServiceImpl implements OrderService{
    public OrderDaoImpl orderDaoImpl=new OrderDaoImpl();
	@Override
	public void savaOrder(Order order) throws Exception{
	
		Connection conn=null;
		try {
			//获取连接
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			
			orderDaoImpl.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDaoImpl.saveOrderItem(conn,item);	
			}
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
		
	}
	@Override
	public Order findOrderByOid(String id) throws Exception {
		
		return orderDaoImpl.findOrderByOid(id);
	}
	@Override
	public void updateOrder(Order order) throws Exception {
		orderDaoImpl.updateOrder(order);
		
	}
	@Override
	public PageModel findMyOrderByUid(User user, int num) throws Exception {
		Connection conn=null;
		PageModel pm=null;
		try {
			//获取连接
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//我的订单的总数
			int totalRecords=orderDaoImpl.getTotalRecords(conn,user);
			//pagemodel计算 各个值
			 pm=new PageModel(num,totalRecords,3);
			List<Order> list=orderDaoImpl.findMyOrderByUid(conn,user, pm.getStartIndex(), pm.getPageSize());
			pm.setList(list);
			pm.setUrl("OrderServlet?method=findMyOrderByUid");
		    conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
		return pm;
	}

}

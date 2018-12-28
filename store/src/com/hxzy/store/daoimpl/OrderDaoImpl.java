package com.hxzy.store.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hxzy.store.dao.OrderDao;
import com.hxzy.store.domain.Order;
import com.hxzy.store.domain.OrderItem;
import com.hxzy.store.domain.Product;
import com.hxzy.store.domain.User;
import com.hxzy.store.util.JDBCUtils;

public class OrderDaoImpl implements OrderDao{

	@Override
	public void saveOrder(Connection conn,Order order) throws Exception {
		QueryRunner qr=new QueryRunner();
		Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		qr.update(conn,"INSERT INTO store.order VALUES(?,?,?,?,?,?,?,?)",params);
	}

	@Override
	public void saveOrderItem(Connection conn,OrderItem item) throws Exception {
        QueryRunner qr=new QueryRunner();
		Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		qr.update(conn,"insert into store.orderitem values(?,?,?,?,?)",params);
		
		
	}

	@Override
	public Order findOrderByOid(String id) throws Exception {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select *  from store.order where oid=?", new BeanHandler<Order>(Order.class),id);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update("update store.order set state=?,address=?,name=?,telephone=? where oid=?",params);
	}

	@Override
	public int getTotalRecords(Connection conn,User user) throws Exception {
		QueryRunner qr=new QueryRunner();
		Long c=(Long) qr.query(conn,"select count(*) from store.order where uid=?", new ScalarHandler(),user.getUid());
	    return c.intValue();
	}

	@Override
	public List<Order> findMyOrderByUid(Connection conn,User user, int startIndex, int pageSize) throws Exception{
		QueryRunner qr=new QueryRunner();
	
		List<Order> list=qr.query(conn,"select * from  store.order where uid=? limit ?,?", new BeanListHandler<Order>(Order.class),user.getUid(), startIndex,pageSize);
		
		for(Order o:list){
			String oid=o.getOid();
			List<Map<String,Object>> list02=qr.query(conn,"select * from  store.orderitem o, store.product p where o.pid=p.pid and oid=?", new MapListHandler(),oid);
			
			List<OrderItem> list03=new ArrayList<OrderItem>();
			for(Map<String,Object> map:list02){
				  OrderItem item=new OrderItem();
				  Product p=new Product();
				  //创建日期转换器
				  DateConverter dt=new  DateConverter();
				  //设置日期的格式
				  dt.setPattern("yyyy-MM-dd");
				  //注册装换器
				  ConvertUtils.register(dt, java.util.Date.class);
				  BeanUtils.populate(item, map);
				  BeanUtils.populate(p, map);
				  item.setProduct(p);
				  list03.add(item);
			}
			
			o.setList(list03);
		}
		
		return list;
	}
	
	

}

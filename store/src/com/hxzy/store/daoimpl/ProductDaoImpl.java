package com.hxzy.store.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hxzy.store.dao.ProductDao;
import com.hxzy.store.domain.Product;
import com.hxzy.store.util.JDBCUtils;

public class ProductDaoImpl implements ProductDao{

	@Override
	public List<Product> findHots() throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from product where is_hot=1 and pflag=0  limit 9", new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from product where pid=?", new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public int findTotalRecords(String cid) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	    Long num=(Long)	qr.query("select count(*) from product where cid=? ", new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public List findProductByIdWithPage(String cid, int startIndex, int pageSize) throws SQLException {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from product where cid=? limit ?,?", new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}

}

package com.hxzy.store.domain;

public class CartItem {
	private Product product;//商品
	private int num;//数量
	private double subTotal;//小计
	
	public double getSubTotal() {
		return product.getShop_price()*num;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	

}

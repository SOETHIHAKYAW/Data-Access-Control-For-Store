package com.mystore.model;

public class Phone {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;

	public Phone() {
		super();
	}

	// for show phone-collection.jsp
	public Phone(String goodsid, String name, double price) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
	}

	// for new table to create phone from buy.
	public Phone(String goodsid, String name, double price, int quantity) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Phone [id=" + id + ", goodsid=" + goodsid + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

}

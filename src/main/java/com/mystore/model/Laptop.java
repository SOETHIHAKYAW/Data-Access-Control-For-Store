package com.mystore.model;

public class Laptop {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;
	private String imgname;

	public Laptop() {
		super();
	}

	// for show laptop-collection.jsp
	public Laptop(String goodsid, String name, double price) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
	}

	// for new table to create laptop from buy.
	public Laptop(String goodsid, String name, double price, int quantity, String imgname) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.setImgname(imgname);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", goodsid=" + goodsid + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

//		Date today = new Date(new java.util.Date().getTime());
//		
////		java.util.Date today = new java.util.Date();
////		java.sql.Date sqlDate = new java.sql.Date(today.getTime());
//		this.stockout = today;
//		today = stockout;

}

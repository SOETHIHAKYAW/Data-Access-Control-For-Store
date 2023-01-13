package com.mystore.model;

import java.sql.Date;

public class Items {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;
	private Date stockin;
	private String imgname;

	public Items(int id, String goodsid, String name, double price, int quantity, Date stockin, String imgname) {
		super();
		this.id = id;
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.stockin = stockin;
		this.imgname = imgname;
	}
	
	public Items(String goodsid, String name, double price, int quantity, Date stockin,String imgname) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.stockin = stockin;
		this.imgname = imgname;
	}

	public Items(String goodsid, String name, double price, int quantity, Date stockin) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.stockin = stockin;
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

	public Date getStockin() {
		return stockin;
	}

	public void setStockin(Date stockin) {
		this.stockin = stockin;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", goodsid=" + goodsid + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + ", stockin=" + stockin + "]";
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

}

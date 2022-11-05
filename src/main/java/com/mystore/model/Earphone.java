package com.mystore.model;

public class Earphone {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;
	
	public Earphone() {
		super();
	}

	// for show Earphone-collection.jsp
	public Earphone(String goodsid, String name, double price) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
	}

	// for new table to create Earphone from buy.
	public Earphone(String goodsid, String name, double price, int quantity) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;

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

	@Override
	public String toString() {
		return "Earphone [id=" + id + ", goodsid=" + goodsid + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

}

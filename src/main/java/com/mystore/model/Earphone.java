package com.mystore.model;

public class Earphone {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;
	private String imgname;
	
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
	public Earphone(String goodsid, String name, double price, int quantity, String imgname) {
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

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

}

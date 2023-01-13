package com.mystore.model;

public class SaleItems {

	private int id;
	private String goodsid;
	private String name;
	private double price;
	private int quantity;
	private String imgname;

	public SaleItems() {
		super();
	}

	public SaleItems(int id, String goodsid, String name, double price, int quantity) {
		super();
		this.id = id;
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public SaleItems(String goodsid, String name, double price, int quantity) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public SaleItems(int id,String goodsid, String name, double price, int quantity, String imgname) {
		super();
		this.id = id;
		this.goodsid = goodsid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imgname = imgname;
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

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	@Override
	public String toString() {
		return "SaleItems [id=" + id + ", goodsid=" + goodsid + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + ", imgname=" + imgname + "]";
	}

}

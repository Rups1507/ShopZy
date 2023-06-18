package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable{
	private int id;
	private String name;
	private int qty;
	private double price;
	private String category;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, int qty, double price, String category) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + ", category=" + category
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, name, price, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && qty == other.qty;
	}
	
	
	
}

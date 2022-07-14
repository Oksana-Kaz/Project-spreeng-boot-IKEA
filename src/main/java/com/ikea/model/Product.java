package com.ikea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Table(name = "Product")
@Entity
public class Product {

	@Id
	@Column(name = "Id")
	private int id;
	@Column(name = "Name")
	@JsonView(value = { View.viewProduct.viewProductByCategory.class })
	private String name;
	@Column(name = "Code")
	@JsonView(value = { View.viewProduct.viewProductByCategory.class })
	private String code;
	@Column(name = "Category")
	private String category;
	@Column(name = "Price")
	private double price;
	@Column(name = "Quantity")
	@JsonView(value = { View.viewProduct.viewProductIs.class })
	private int quantity;
	@Column(name = "InStock")
	@JsonView(value = { View.viewProduct.viewProductAvailable.class })
	private boolean inStock;
	@Column(name = "Rating")
	@JsonView(value = { View.viewProduct.viewProductByCategory.class })
	private int rating;

	public Product() {
		super();
	}

	public Product(int id, String name, String code, String category, double price, int quantity, boolean inStock,
			int rating) {

		this.id = id;
		this.name = name;
		this.code = code;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.inStock = inStock;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public int getRating() {
		return rating;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", code=" + code + ", category=" + category + ", price=" + price
				+ ", quantity=" + quantity + ", inStock=" + inStock + ", rating=" + rating + "]";
	}

}

package edu.osu.cse5234.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CUSTOMER_ORDER_LINE_ITEM")
public class LineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="ITEM_NUMBER")
	private int itemNumber;
	
	@Column(name="ITEM_NAME")
	private String name;
	
	@Transient
	private double price;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@Column(name="CUSTOMER_ORDER_ID_FK")
	private int CUSTOMER_ORDER_ID_FK;
	
	public LineItem(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
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

	public int getCUSTOMER_ORDER_ID_FK() {
		return CUSTOMER_ORDER_ID_FK;
	}

	public void setCUSTOMER_ORDER_ID_FK(int cUSTOMER_ORDER_ID_FK) {
		CUSTOMER_ORDER_ID_FK = cUSTOMER_ORDER_ID_FK;
	}
	
	
}

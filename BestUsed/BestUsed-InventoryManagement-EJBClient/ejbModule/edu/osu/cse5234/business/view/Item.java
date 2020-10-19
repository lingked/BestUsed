package edu.osu.cse5234.business.view;

public class Item implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2380437669506043346L;
	String name;
	String price;
	String quantity;
	String imgUrl;
	String reservation;
	
	public Item (){
		this.name = null;
		this.price = null;
		this.quantity = null;
		this.reservation = null;
	}
	public Item (String name, String price, String quantity, String imgUrl, String reservation) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imgUrl = imgUrl;
		this.reservation = reservation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
}

package edu.osu.cse5234.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SHIPPING_INFO")
public class ShippingInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	String id;
	
	@Transient
	String name;
	
	@Column(name="ADDRESS1")
	String addressLine1;
	
	@Column(name="ADDRESS2")
	String addressLine2;
	
	@Column(name="CITY")
	String city;
	
	@Column(name="STATE")
	String state;
	
	@Column(name="POSTAL_CODE")
	String zip;
	
	@Column(name="EMAIL")
	String email;
	
	@Column(name="COUNTRY")
	String country;
	
	public ShippingInfo (){
		this.name = null;
		this.addressLine1 = null;
		this.addressLine2 = null;
		this.city = null;
		this.state = null;
		this.zip = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}


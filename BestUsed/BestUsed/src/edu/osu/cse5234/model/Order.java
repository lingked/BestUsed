package edu.osu.cse5234.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {
	
	public Order(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="CUSTOMER_EMAIL")
	private String emailAddress;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ORDER_ID_FK")
	private List<LineItem> lineItems;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="SHIPPING_INFO_ID_FK")
	private ShippingInfo shippinginfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PAYMENT_INFO_ID_FK")
	private PaymentInfo paymentInfo;
	
	@Column(name="STATUS")
	private String status="New";
	
	
	
	public void addItem(LineItem item) {
		lineItems.add(item);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> items) {
		lineItems = items;
	}

	public ShippingInfo getShippinginfo() {
		return shippinginfo;
	}

	public void setShippinginfo(ShippingInfo shippinginfo) {
		this.shippinginfo = shippinginfo;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
//	List<edu.osu.cse5234.business.view.Item> Items;
//	public Order () {
//		this.Items = new ArrayList<>();
//	}
//	public List<edu.osu.cse5234.business.view.Item> getItems() {
//		return this.Items;
//	}
//	public void setItems(List<edu.osu.cse5234.business.view.Item> list) {
//		this.Items = list;
//	};
}

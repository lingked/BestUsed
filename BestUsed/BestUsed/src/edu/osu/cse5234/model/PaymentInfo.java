package edu.osu.cse5234.model;

public class PaymentInfo {
	 String creditCardNumber;
	 String expirationDate;
	 String cvvCode;
	 String cardHolderName;
	 public PaymentInfo (){
		 this.cardHolderName = null;
		 this.expirationDate = null;
		 this.cvvCode = null;
		 this.creditCardNumber = null;
	 }
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	 
}

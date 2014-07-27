package org.arbusta.model.account;

public class Price {
	double price;
	String currency;

	public Price(double price, String currency) {
		this.price = price;
		this.currency = currency;
	}
	
	public Price(double price) {
		this(price, "USD");
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return "[" + price + " " + currency + "]";
	}
}

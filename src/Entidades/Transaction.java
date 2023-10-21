package Entidades;

 

public class Transaction {
private Currency currency;
private int price;

private void setPrice() {
	if (this.currency!=null) {
		this.price = this.currency.getPrice();
	}
}
public Transaction(Currency currency) {
	super();
	this.currency = currency;
  this.setPrice();

}
 
public Currency getCurrency() {
	return currency;
}
public int getPrice() {
	return price;
}
@Override
public String toString() {
	return "Transaction [currency=" + currency + ", price=" + price + "]";
}

}
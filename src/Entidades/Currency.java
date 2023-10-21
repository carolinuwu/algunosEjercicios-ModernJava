package Entidades;

public class Currency {
private final int price;


public Currency(int price) {
	super();
	this.price = price;
}

@Override
public String toString() {
	return "Currency [price=" + price + "]";
}

public int getPrice() {
	return price;
}

}
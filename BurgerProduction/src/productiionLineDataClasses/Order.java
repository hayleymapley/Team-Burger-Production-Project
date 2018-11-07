package productiionLineDataClasses;

import java.util.List;

public class Order {
	
	private int orderID;
	
	private Customer customer;
	
	private List<Burger> burgers;
	
	public Order(int orderID, Customer customer, List<Burger> burgers) {
		this.orderID = orderID;
		this.customer = customer;
		this.burgers = burgers;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Burger> getBurgers() {
		return burgers;
	}

	public void setBurgers(List<Burger> burgers) {
		this.burgers = burgers;
	}
	

}

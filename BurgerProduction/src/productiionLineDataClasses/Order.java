package productiionLineDataClasses;


import java.sql.Timestamp;
import java.util.List;

public class Order {
	private boolean completed;
	private Timestamp timestamp;
	private int orderID;
	private Customer customer;
	private List<Burger> burgers;
	
	public Order(boolean completed, Timestamp timestamp, int orderID, Customer customer, List<Burger> burgers) {
		this.completed =completed;
		this.setTimestamp(timestamp);
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


	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

}

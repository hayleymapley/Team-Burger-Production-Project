package handlers;

import java.util.List;

import productiionLineDataClasses.Order;

public class OrderHandler {

	private List<Order> orders;


	public List<Order> getOrders() { //this will help in future for testing purposes
		return orders;
	}

	public List<Order> retrieveOrdersFromDB() {

		//TODO: goes to DB and retrieves orders

		return this.orders;

	}

	public boolean setOrderToComplete(Order order) {

		//TODO: method is called upon when "complete order" button is pressed in UI

		return false;


}
}

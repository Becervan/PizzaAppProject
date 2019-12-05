package core;

import java.util.ArrayList;

public class Order {
	private int id;
	private String orderType;
	private Customer customer;
	private Payment payment;
	private ArrayList<OrderItem> items;
	
	public Order(int id) {
		this.id = id;
		
		orderType = "Pickup";
		customer = null;
		payment = null;
		items = new ArrayList<OrderItem>();
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	
	//Setters
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void makePayment(Payment payment) {
		this.payment = payment;
	}
	
	
	//Getters
	public int getId() {
		return id;
	}
	
	public String getOrderType() {
		return orderType;
	}
	
	public String getCustomerName() {
		if(customer == null) return "";
		return customer.getFirstName()+" "+customer.getLastName();
	}
	public String getCustomerPhone() {
		if(customer == null) return "";
		return customer.getPhone();
	}
	public String getCustomerAddress() {
		if(customer == null) return "";
		return customer.getAddress();
	}
	
	public double getPaymentAmount() {
		if(payment == null) return 0.0;
		return payment.getAmount();
	}
}

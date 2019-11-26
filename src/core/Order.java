package core;

public class Order {
	private int id;
	private Customer customer;
	private Payment payment;
	
	public Order(int id) {
		this.id = id;
		this.customer = null;
		this.payment = null;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void makePayment(Payment payment) {
		this.payment = payment;
	}
}

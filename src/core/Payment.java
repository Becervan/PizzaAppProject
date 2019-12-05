package core;

/* Payment class:
 * Stores information about a payment
 */

public class Payment {
	private int id;
	private double amount;
	
	public Payment(int id, double amount) {
		this.id = id;
		this.amount = amount;
	}
	
	public int getId() { return id; }
	public double getAmount() { return amount; }
	public void setId(int id) { this.id = id; }
	public void setAmount(double amount) { this.amount = amount; }
}

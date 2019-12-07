package core;

public class OrderItem {
	
	private int id;
	private String name;
	private int quantity;
	private double price;
	private String notes;
	
	public OrderItem(int id) {
		this(id, "", 0, 0.0, "");
	}
	public OrderItem(int id, String name, int quantity, double price, String notes) {
		
		this.id = id; 
		this.name = name;
		this.quantity = quantity;
		this.price = price; 
		this.notes = notes;
		
	}	
	
	// Getters
	public int getId() {return id;}
	public String getName() {return name;}
	public int getQuantity() {return quantity;}
	public double getPrice() {return price;}
	public String getNotes() {return notes;}
	
	// Setters
	public void setId(int id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	public void setPrice(double price) {this.price = price;}
	public void setNotes(String notes) {this.notes = notes;}
	
}

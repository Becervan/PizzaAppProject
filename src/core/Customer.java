package core;

/* Customer class:
 * Temporarily stores all the information about a customer
 * 
 * Author(s): John Hussey
 */

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	private String notes;
	
	//Constructors
	public Customer(int id, String firstName, String lastName, String address, String phone, String email, String notes) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.notes = notes;
	}
	
	
	
	//Getters
	public int getId() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getAddress() { return address; }
	public String getPhone() { return phone; }
	public String getEmail() { return email; }
	public String getNotes() { return notes; }
	
	//Setters
	public void setId(int id) { this.id = id; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setAddress(String address) { this.address = address; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setEmail(String email) { this.email = email; }
	public void setNotes(String notes) { this.notes = notes; }
}

package core;

/* Customer class:
 * Temporarily stores all the information about a customer
 */

public class Customer implements Comparable<Customer>{
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String email;
	private String notes;
	
	//Constructors
	public Customer(int id, String firstName, String lastName, String phone, String address, String email, String notes) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.notes = notes;
	}
	
	@Override
	public int compareTo(Customer other) {
		return phone.compareTo(other.getPhone());
	}
	
	@Override
	public String toString() {
		String out = getName()+", "+phone;
		if(address != "") out += ", "+address;
		if(email != "") out += ", "+email;
		if(notes != "") out += ", "+notes;
		return out;
	}
	
	//Setters
	public void setId(int id) { this.id = id; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setAddress(String address) { this.address = address; }
	public void setEmail(String email) { this.email = email; }
	public void setNotes(String notes) { this.notes = notes; }
	
	//Getters
	public int getId() { return id; }
	public String getName() { return firstName +" "+ lastName; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getPhone() { return phone.substring(0, 3) +"-"+phone.substring(3, 6)+"-"+phone.substring(6); }
	public String getAddress() { return address; }
	public String getEmail() { return email; }
	public String getNotes() { return notes; }

	
}

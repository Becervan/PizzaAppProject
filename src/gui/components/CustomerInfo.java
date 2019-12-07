package gui.components;

import core.Customer;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CustomerInfo {
	private Text nameText;
	private Text phoneText;
	private Text address1Text, address2Text;
	private TextField notesField;
	
	//Constructors
	public CustomerInfo(Text nameText, Text phoneText, Text address1Text, Text address2Text) {
		this(nameText, phoneText, address1Text, address2Text, null);
	}
	public CustomerInfo(Text nameText, Text phoneText, Text address1Text, Text address2Text, TextField notesField) {
		this.nameText = nameText;
		this.phoneText = phoneText;
		this.address1Text = address1Text;
		this.address2Text = address2Text;
		this.notesField = notesField;
		setText(nameText, "No customer");
		setText(phoneText, "");
		setText(address1Text, "");
		setText(address2Text, "");
		if(this.notesField != null) notesField.setText("");
	}
	
	public void update(Customer customer) {
		String address = customer.getAddress();
		
		setText(nameText, customer.getName());
		setText(phoneText, customer.getPhone());
		
		int i = address.indexOf(",");
		setText(address1Text, address.substring(0, i));
		setText(address2Text, address.substring(i+2));
		
		if(this.notesField != null) notesField.setText(customer.getNotes());
	}
	
	//Setters
	private void setText(Text textBox, String text) {
		textBox.setText(text);
	}
	
	//Getters
	public String getName() {
		return nameText.getText();
	}
	
	public String getPhone() {
		return phoneText.getText();
	}
	
	public String getAddress1() {
		return address1Text.getText();
	}
	
	public String getAddress2() {
		return address2Text.getText();
	}
}

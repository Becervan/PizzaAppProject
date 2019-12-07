package gui.components;

import javafx.scene.control.TextField;

public class PhoneInput {
	private TextField textField;
	
	//Constructors
	public PhoneInput(TextField textField) {
		this(textField, "###-###-####", "grey");
	}
	public PhoneInput(TextField textField, String text, String fontColor) {
		this.textField = textField;
		setText(text);
		setFontColor(fontColor);
		setMaxChars(12);
	}
	
	public String update() {
		String value = getText();
		String temp = value.replace("#", "").replace("-", "");
		
		String fontColor = "black";
		String result = temp;
		int n = temp.length();
		
		if(n > 10) 		result = temp.substring(0, 3) +"-"+ temp.substring(3, 6) +"-"+ temp.substring(6, 10);
    	else if(n > 6) 	result = temp.substring(0, 3) +"-"+ temp.substring(3, 6) +"-"+ temp.substring(6);
    	else if(n > 3) 	result = temp.substring(0, 3) +"-"+ temp.substring(3);
    	else if(n == 0) {
    		result = "###-###-####";
    		fontColor = "grey";
    	}
		
		temp = result;
		if(temp.startsWith("#")) temp = "";
    	while(result.length() < 12) {
    		if(result.length() == 3 || result.length() == 7) result += "-";
    		else result += "#";
    	}
    	
    	setFontColor(fontColor);
    	setText(result);
    	setPosition(temp.length());
    	return temp;
	}
	
	//Setters
	public void setText(String text) {
		textField.setText(text);
	}
	
	private void setFontColor(String fontColor) {
		textField.setStyle("-fx-text-inner-color: "+fontColor);
	}
	
	private void setPosition(int position) {
		textField.positionCaret(position);
	}
	
	private void setMaxChars(int maxChars) {
		textField.setPrefColumnCount(maxChars);
	}
	
	//Getters
	private String getText() {
		return textField.getText();
	}
}

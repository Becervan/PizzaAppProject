package gui.components;

import javafx.scene.text.Text;

public class OrderInfo {
	private int orderNum;
	private String orderType;
	private double subtotal, taxes, fees, total;
	
	private Text orderNumText;
	private Text orderTypeText;
	private Text subtotalText;
	private Text taxesText;
	private Text feesText;
	private Text totalText;
	
	public OrderInfo(Text orderNumText, Text orderTypeText, Text subtotalText, Text taxesText, Text feesText, Text totalText) {		
		this.orderNumText = orderNumText;
		this.orderTypeText = orderTypeText;
		this.subtotalText = subtotalText;
		this.taxesText = taxesText;
		this.feesText = feesText;
		this.totalText = totalText;
		
		this.orderNum = 0;
		newOrder();
	}
	
	public void update() {
		setText(this.orderNumText, orderNum+"");
		setText(this.orderTypeText, orderType);
		setText(this.subtotalText, subtotal+"");
		setText(this.taxesText, taxes+"");
		setText(this.feesText, fees+"");
		setText(this.totalText, total+"");
	}
	
	public void newOrder() {
		orderNum++;
		orderType = "Pickup";
		subtotal = taxes = fees = total = 0.0;
		update();
	}
	
	public void setOrderType(String orderType) {
		setText(orderTypeText, orderType);
	}
	
	//Setters
	private void setText(Text textBox, String text) {
		textBox.setText(text);
	}
}

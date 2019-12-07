package gui.components;

import javafx.scene.control.ListView;
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
	private ListView<String> orderItemList;
	
	public OrderInfo(Text orderNumText, Text orderTypeText, Text subtotalText, Text taxesText, Text feesText, Text totalText, ListView<String> orderItemList) {		
		this.orderNumText = orderNumText;
		this.orderTypeText = orderTypeText;
		this.subtotalText = subtotalText;
		this.taxesText = taxesText;
		this.feesText = feesText;
		this.totalText = totalText;
		this.orderItemList = orderItemList;
		
		this.orderNum = 0;
		newOrder();
	}
	
	private String formatCurrency(double num) {
		String res = num+"";
		if(res.substring(res.indexOf('.')).length() < 3)
			return res+"0";
		return res;
	}
	
	public void update() {
		setText(this.orderNumText, orderNum+"");
		setText(this.orderTypeText, orderType);
		setText(this.subtotalText, formatCurrency(subtotal));
		setText(this.taxesText, formatCurrency(taxes));
		setText(this.feesText, formatCurrency(fees));
		setText(this.totalText, formatCurrency(total));
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

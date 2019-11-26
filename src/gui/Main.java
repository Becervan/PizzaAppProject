package gui;

import core.Order;

public class Main {
	
	public static void main(String[] args) {
		//Init core
		Order order = new Order(0);
		
		//Init gui
		Window window = new Window("The Oven - POS", 1920, 1080);
	}

}

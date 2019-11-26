package gui;

import java.util.ArrayList;
import gui.component.Component;

public class Window {
	private String title;
	private int width, height;
	private ArrayList<Component> components;
	
	public Window() { 
		this("POS", 1920, 1080);
	}
	
	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.components = new ArrayList<Component>();
	}
	
	public void addComponent(Component component) {
		this.components.add(component);
	}
	
	public void close() {
		
	}
}

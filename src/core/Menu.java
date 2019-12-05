package core;

import java.util.ArrayList;

public class Menu extends MenuItem {
	private static final int MAX_LEVEL = 3;
		
	private int level;
	private ArrayList<MenuItem> options;
	private MenuItem selection;
	
	//Constructors
	public Menu() {
		this(0);
	}
	public Menu(int level) {
		this(level, new ArrayList<MenuItem>());
	}
	public Menu(int level, ArrayList<MenuItem> options) {
		this.level = level;
		this.options = options;
		selection = null;
	}
	
	public void addItem(MenuItem item) {
		options.add(item);
	}
	
	public void selectItem(int index) {
		selection = options.get(index);
	}
	
	//Navigation
	public int prevLevel() {
		if(level > 0)
			return level-1;
		return level;
	}
	public int nextLevel() {
		if(level < MAX_LEVEL)
			return level+1;
		return level;
	}
	
	//Getters
	public int getLevel() {
		return level;
	}
	
	public MenuItem getSelection() {
		return selection;
	}
}

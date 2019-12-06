package gui.components;

import javafx.scene.control.Button;

public class NavBar {
	private Menu menu;
	private Button[] options;
	private Button logoutButton;
	
	private String menuText;
	
	public NavBar(Menu menu, Button[] options, Button logoutButton) {
		this.menu = menu;
		this.options = options;
		this.logoutButton = logoutButton;
		
		for(int i = 0; i < options.length; i++) {
    		final int index = i;
    		this.options[i].setOnAction(e -> selectItem(index));
    	}
		this.logoutButton.setOnAction(e -> logout());
		menuText = "Menu";
	}
	
	private void selectItem(int index) {
		String oldTitle = menu.getTitle();
		String newTitle = options[index].getText();
		Button leftBtn = menu.getLeftBtn();
		Button rightBtn = menu.getRightBtn();
		Button selected = options[index];
		
		for(int i = 0; i < options.length; i++) {
			Button btn = options[i];
			btn.setDisable(i == index);
			btn.setDefaultButton(false);
		}
		selected.setDefaultButton(true);
		
		if(oldTitle.startsWith("Menu")) {
			menuText = oldTitle;
		} else if(oldTitle.startsWith("Orders")) {
			leftBtn.setText("Back");
			rightBtn.setText("Done");
		}
		
		if(selected.getText().startsWith("Menu")) {
			newTitle = menuText;
		} else if(selected.getText().startsWith("Orders")) {
			leftBtn.setText("Edit");
			rightBtn.setText("Print Receipt");
		}

		menu.setTitle(newTitle);
		if(newTitle.startsWith("Menu")) menu.enableButtons();
		else menu.disableButtons();
	}
	
	private void logout() {
		System.exit(0);
	}
}

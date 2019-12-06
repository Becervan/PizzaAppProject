package gui.components;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Menu {	
	private Text titleText;
	private GridPane navGrid;
	private GridPane gridPane;
	private ArrayList<Button> options;
	private Button backButton;
	private Button doneButton;
	
	private ClassLoader loader = getClass().getClassLoader();
	private Image pizzaIcon = new Image(loader.getResourceAsStream("res/icons/menu/pizza.png"));
	private Image sidesIcon = new Image(loader.getResourceAsStream("res/icons/menu/sides.png"));
	private Image drinksIcon = new Image(loader.getResourceAsStream("res/icons/menu/drinks.png"));
	private Image saladsIcon = new Image(loader.getResourceAsStream("res/icons/menu/salads.png"));
	private Image dessertIcon = new Image(loader.getResourceAsStream("res/icons/menu/dessert.png"));
	private Image pepsiIcon = new Image(loader.getResourceAsStream("res/icons/menu/pepsi.png"));
	
	private int level;
	
	public Menu(Text titleText, GridPane navGrid, GridPane gridPane, Button backButton, Button doneButton) {
		this.titleText = titleText;
		this.navGrid = navGrid;
		this.gridPane = gridPane;
		this.backButton = backButton;
		this.doneButton = doneButton;
		level = 1;
		
		options = new ArrayList<Button>();
		for(Node x : gridPane.getChildren())
			if(x.getClass() == Button.class) options.add((Button)x);
		
		for(int i = 0; i < options.size(); i++) {
    		final int index = i;
    		options.get(i).setOnAction(e -> selectItem(index));
    	}
		this.backButton.setOnAction(e -> goBack());
		this.doneButton.setOnAction(e -> onDone());
	}
	
	private void updateButton(int index, String text, Image icon) {
    	updateButton(index, text, icon, false, true);
    }
    private void updateButton(int index, String text, Image icon, boolean disabled) {
    	updateButton(index, text, icon, disabled, true);
    }
    private void updateButton(int index, String text, Image icon, boolean disabled, boolean visible) {
    	Button button = options.get(index);
    	ImageView graphic = (ImageView)button.getGraphic();
    	graphic.setImage(icon);
    	button.setGraphic(graphic);
    	button.setText(text);
    	button.setDisable(disabled);
    	button.setVisible(visible);
    }
	
    private void selectItem(int index) {
    	Button selected = options.get(index);
    	titleText.setText(titleText.getText() +" > "+ selected.getText());
    	
    	if(level == 1) {
    		if(index == 0) {
    			//go to pizza menu
    			updateButton(0, "Create Your Own", pizzaIcon);
    			updateButton(1, "Pepperoni", pizzaIcon);
    			updateButton(2, "Cheese", pizzaIcon);
    			updateButton(3, "Meat Mania", pizzaIcon);
    			updateButton(4, "Supreme", pizzaIcon);
    			updateButton(5, "Veggie", pizzaIcon);
    		} else if(index == 1) {
    			//go to sides menu
    			updateButton(0, "Chicken Wings", sidesIcon, true);
    			updateButton(1, "Bread Sticks", sidesIcon, true);
    			updateButton(2, "Mac \'N Cheese", sidesIcon, true);
    			updateButton(3, "Pasta", sidesIcon, true);
    			updateButton(4, "", null, true, false);
    			updateButton(5, "", null, true, false);
    		} else if(index == 2) {
    			//go to drinks menu
    			updateButton(0, "Pepsi", pepsiIcon);
    			updateButton(1, "Diet Pepsi", pepsiIcon);
    			updateButton(2, "Dr. Pepper", drinksIcon);
    			updateButton(3, "Mountain Dew", drinksIcon);
    			updateButton(4, "Sierra Mist", drinksIcon);
    			updateButton(5, "Surge", drinksIcon);
    		}
    		backButton.setDisable(false);
    	} else if(level == 2) {
    		for(Button btn : options) btn.setDisable(true);
    		doneButton.setDisable(false);
    	}
    	if(level < 3) level++;
    }
	
    public void goBack() {
    	String title = getTitle().replace("  ", " ");
    	int index = title.lastIndexOf(">");
    	if(index >= 0) setTitle(title.substring(0, index));
    	
    	if(level == 2) {
    		updateButton(0, "Pizza", pizzaIcon);
    		updateButton(1, "Sides", sidesIcon);
    		updateButton(2, "Drinks", drinksIcon);
    		updateButton(3, "Salads", saladsIcon, true);
    		updateButton(4, "Dessert", dessertIcon, true);
    		updateButton(5, "", null, true, false);
    		
    		backButton.setDisable(true);
    		level--;
    	} else if(level == 3) {
    		for(Button btn : options) btn.setDisable(false);
    		doneButton.setDisable(true);
    		level--;
    	}
    }
    
    public void reset() {
    	while(level > 1) goBack();
    }
	public void onDone() {
		//add item (with options) to order
		
		reset();
	}
	
	public void enableButtons() {
		gridPane.setDisable(false);
		navGrid.setDisable(false);
	}
	
	public void disableButtons() {
		gridPane.setDisable(true);
		navGrid.setDisable(true);
	}
	
	public void setTitle(String text) {
		titleText.setText(text);
	}
	
	public String getTitle() {
		return titleText.getText();
	}
	
	public Button getLeftBtn() {
		return backButton;
	}
	public Button getRightBtn() {
		return doneButton;
	}
}

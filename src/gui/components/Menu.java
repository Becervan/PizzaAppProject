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
		this.level = 1;
		
		options = new ArrayList<Button>();
		for(Node x : gridPane.getChildren()) {
			if(x.getClass() == Button.class) {
				options.add((Button)x);
			}
		}
		
		for(int i = 0; i < options.size(); i++) {
    		final int index = i;
    		options.get(i).setOnAction(e -> selectItem(index));
    	}
		this.backButton.setOnAction(e -> goBack());
		this.doneButton.setOnAction(e -> onDone());
	}
	
	private void updateButtons(String[] texts, Image[] icons, boolean[] disable) {
		for(int i = 0; i < texts.length; i++) {
			Button btn = options.get(i);
			ImageView graphic = (ImageView)btn.getGraphic();
			graphic.setImage(icons[i]);
			
			btn.setGraphic(graphic);
			btn.setText(texts[i]);
			btn.setVisible(texts[i].length() > 0);
			
			if(i < disable.length) btn.setDisable(disable[i]);
			else btn.setDisable(disable[disable.length-1]);
		}
	}
	
    private void selectItem(int index) {
    	Button selected = options.get(index);
    	titleText.setText(titleText.getText() +" > "+ selected.getText());
    	
    	if(level == 1) {
    		if(index == 0) {
    			//go to pizza menu
    			String[] texts = new String[]{"Create Your Own", "Pepperoni", "Cheese", "Meat Mania", "Supreme", "Veggie"};
    			Image[] icons = new Image[]{pizzaIcon, pizzaIcon, pizzaIcon, pizzaIcon, pizzaIcon, pizzaIcon};
    			boolean[] disabled = new boolean[]{false};
    			updateButtons(texts, icons, disabled);
    		} else if(index == 1) {
    			//go to sides menu
    			String[] texts = new String[]{"Chicken Wings", "Bread Sticks", "Mac \'N Cheese", "Pasta", "", ""};
    			Image[] icons = new Image[]{sidesIcon, sidesIcon, sidesIcon, sidesIcon, null, null};
    			boolean[] disabled = new boolean[]{true};
    			updateButtons(texts, icons, disabled);
    		} else if(index == 2) {
    			//go to drinks menu
    			String[] texts = new String[]{"Pepsi", "Diet Pepsi", "Dr. Pepper", "Mountain Dew", "Sierra Mist", "Surge"};
    			Image[] icons = new Image[]{pepsiIcon, pepsiIcon, drinksIcon, drinksIcon, drinksIcon, drinksIcon};
    			boolean[] disabled = new boolean[]{false};
    			updateButtons(texts, icons, disabled);
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
    		//go back to level 1
    		String[] texts = new String[]{"Pizza", "Sides", "Drinks", "Salads", "Dessert", ""};
			Image[] icons = new Image[]{pizzaIcon, sidesIcon, drinksIcon, saladsIcon, dessertIcon, null};
			boolean[] disabled = new boolean[]{false, false, false, true};
    		updateButtons(texts, icons, disabled);
    	} else if(level == 3) {
    		//go back to level 2
    		for(Button btn : options) btn.setDisable(false);
    		doneButton.setDisable(true);
    	}
    	if(level > 1) level--;
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

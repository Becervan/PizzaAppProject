package gui.components;

import java.util.ArrayList;

import core.Order;
import core.OrderItem;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Menu {
	private Order order;
	private OrderInfo orderInfo;
	private OrderItem orderItem;
	
	private String category;
	private Text titleText;
	private GridPane navGrid;
	private GridPane gridPane;
	private ArrayList<Button> options;
	private Button backButton;
	private Button doneButton;
	
	private VBox level3Box;
	private GridPane level3Options;
	private GridPane level3Size;
	private GridPane level3Crust;
	private GridPane level3Toppings;
	private Text sizeText;
	private Text crustText;
	private ArrayList<Button> sizeButtons;
	private ArrayList<Button> crustButtons;
	private ArrayList<ToggleButton> toppingButtons;
	private String[] selectedToppings;
	
	private ClassLoader loader = getClass().getClassLoader();
	private Image pizzaIcon = new Image(loader.getResourceAsStream("res/icons/menu/pizza.png"));
	private Image sidesIcon = new Image(loader.getResourceAsStream("res/icons/menu/sides.png"));
	private Image drinksIcon = new Image(loader.getResourceAsStream("res/icons/menu/drinks.png"));
	private Image saladsIcon = new Image(loader.getResourceAsStream("res/icons/menu/salads.png"));
	private Image dessertIcon = new Image(loader.getResourceAsStream("res/icons/menu/dessert.png"));
	private Image pepsiIcon = new Image(loader.getResourceAsStream("res/icons/menu/pepsi.png"));
	
	private int level;
	
	public Menu(OrderInfo orderInfo, Text titleText, GridPane navGrid, GridPane gridPane, Button backButton, Button doneButton, VBox level3Box, GridPane level3Options, GridPane level3Size, GridPane level3Crust, GridPane level3Toppings) {
		this.order = new Order(1);
		this.orderInfo = orderInfo;
		this.orderItem = null;
		this.titleText = titleText;
		this.navGrid = navGrid;
		this.gridPane = gridPane;
		this.backButton = backButton;
		this.doneButton = doneButton;
		
		this.level3Box = level3Box;
		this.level3Options = level3Options;
		this.level3Size = level3Size;
		this.level3Crust = level3Crust;
		this.level3Toppings = level3Toppings;
		this.selectedToppings = new String[4];
		
		this.level = 1;
		this.category = "";
		
		options = new ArrayList<Button>();
		for(Node x : this.gridPane.getChildren())
			if(x.getClass() == Button.class) options.add((Button)x);
		
		for(int i = 0; i < options.size(); i++) {
    		final int index = i;
    		options.get(i).setOnAction(e -> selectItem(index));
    	}
		this.backButton.setOnAction(e -> goBack());
		this.doneButton.setOnAction(e -> onDone());
		
		
		for(Node x : this.level3Options.getChildren()) {
			if(x.getClass() == Text.class) {
				Text y = (Text)x;
				if(y.getText().equals("Size:")) sizeText = y;
				else if(y.getText().equals("Crust:")) crustText = y;
			}
		}
		
		sizeButtons = new ArrayList<Button>();
		for(Node x : this.level3Size.getChildren())
			if(x.getClass() == Button.class) sizeButtons.add((Button)x);
		
		crustButtons = new ArrayList<Button>();
		for(Node x : this.level3Crust.getChildren())
			if(x.getClass() == Button.class) crustButtons.add((Button)x);
		
		toppingButtons = new ArrayList<ToggleButton>();
		for(Node x : this.level3Toppings.getChildren())
			if(x.getClass() == ToggleButton.class) toppingButtons.add((ToggleButton)x);
		
		
		for(int i = 0; i < sizeButtons.size(); i++) {
			final int index = i;
    		sizeButtons.get(i).setOnAction(e -> selectSize(index));
		}
		
		for(int i = 0; i < crustButtons.size(); i++) {
			final int index = i;
    		crustButtons.get(i).setOnAction(e -> selectCrust(index));
		}
		
		for(int i = 0; i < toppingButtons.size(); i++) {
			final int index = i;
    		toppingButtons.get(i).setOnAction(e -> selectTopping(index));
		}
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
    		category = selected.getText();
    		
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
    		if(category.equals("Pizza")) {
    			for(ToggleButton btn : toppingButtons) btn.setSelected(false);
    			if(index == 1) {
    				toppingButtons.get(0).setSelected(true);
    			} else if(index == 3) {
    				toppingButtons.get(0).setSelected(true);
    				toppingButtons.get(1).setSelected(true);
    				toppingButtons.get(2).setSelected(true);
    				toppingButtons.get(3).setSelected(true);
    				toppingButtons.get(4).setDisable(true);
    				toppingButtons.get(5).setDisable(true);
    				toppingButtons.get(6).setDisable(true);
    				toppingButtons.get(7).setDisable(true);
    			} else if(index == 4) {
    				toppingButtons.get(0).setSelected(true);
    				toppingButtons.get(3).setSelected(true);
    				toppingButtons.get(4).setSelected(true);
    				toppingButtons.get(5).setSelected(true);
    				toppingButtons.get(1).setDisable(true);
    				toppingButtons.get(2).setDisable(true);
    				toppingButtons.get(6).setDisable(true);
    				toppingButtons.get(7).setDisable(true);
    			} else if(index == 5) {
    				toppingButtons.get(4).setSelected(true);
    				toppingButtons.get(5).setSelected(true);
    				toppingButtons.get(6).setSelected(true);
    				toppingButtons.get(7).setSelected(true);
    				toppingButtons.get(0).setDisable(true);
    				toppingButtons.get(1).setDisable(true);
    				toppingButtons.get(2).setDisable(true);
    				toppingButtons.get(3).setDisable(true);
    			}
    			
    			sizeButtons.get(0).setText("8\"");
    			sizeButtons.get(1).setText("10\"");
    			sizeButtons.get(2).setText("14\"");
    			sizeButtons.get(3).setText("18\"");
    			
    			crustText.setVisible(true);
    			level3Crust.setVisible(true);
    			level3Toppings.setVisible(true);
    			gridPane.setVisible(false);
				level3Box.setVisible(true);
    		} else if(category.equals("Drinks")) {
    			sizeButtons.get(0).setText("20-oz");
    			sizeButtons.get(1).setText("2-liter");
    			sizeButtons.get(2).setText("Keg");
    			sizeButtons.get(3).setText("Barrel");
    			
    			crustText.setVisible(false);
    			level3Crust.setVisible(false);
    			level3Toppings.setVisible(false);
    			gridPane.setVisible(false);
				level3Box.setVisible(true);
    		}
    		
    		for(Button btn : options) btn.setDisable(true);
    		doneButton.setDisable(false);
    	}
    	if(level < 3) level++;
    }
    	
    private void selectSize(int index) {
    	
    }
    
    private void selectCrust(int index) {
    	
    }
    
    private void selectTopping(int index) {
    	int i = 0;
    	for(ToggleButton btn : toppingButtons) {
    		if(btn.isSelected()) {
    			selectedToppings[i] = "+"+btn.getText();
    			i++;
    		}
    	}
    	
    	if(i < 4) {
    		for(ToggleButton btn : toppingButtons)
    			if(!btn.isSelected()) btn.setDisable(false);
    	} else {
    		for(ToggleButton btn : toppingButtons) {
    			if(!btn.isSelected()) btn.setDisable(true);
    		}
    	}
    	
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
    		level3Box.setVisible(false);
    		gridPane.setVisible(true);
			
    		for(Button btn : options) btn.setDisable(false);
    		doneButton.setDisable(true);
    	}
    	if(level > 1) level--;
    }
    
    public void reset() {
    	while(level > 1) goBack();
    	order = new Order(order.getId()+1);
    }
    
	public void onDone() {
		//add item (with options) to order
		order.addItem(orderItem);
		orderInfo.update();
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
	
	public Order getOrder() {
		return order;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	
	public Button getLeftBtn() {
		return backButton;
	}
	public Button getRightBtn() {
		return doneButton;
	}
}

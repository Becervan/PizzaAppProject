package res.layout;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import core.Order;
import core.TimeUpdater;
import gui.components.PhoneInput;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class MainController {
	private String searchQuery;
	private PhoneInput customerSearchInput;
	private Order order;
	private int orderNum;
    
    //Order type buttons
    @FXML
    private ToggleGroup order_type;
    @FXML
    private ToggleButton pickup_btn;
    @FXML
    private ToggleButton delivery_btn;
    
    //Customer lookup
    @FXML
    private TextField customer_search_input;
    
    //Nav bar
    @FXML
    private ToggleGroup nav_bar_main;
    @FXML
    private ToggleButton menu_btn;
    @FXML
    private ToggleButton coupons_btn;
    @FXML
    private ToggleButton orders_btn;
    @FXML
    private ToggleButton functions_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Text time_text;
    
    
    //Orders pane
    @FXML
    private Text order_num_text;
    @FXML
    private Text order_type_text;
    
    //Customer info
    @FXML
    private Text customer_name;
    @FXML
    private Text customer_phone;
    @FXML
    private Text customer_address_1;
    @FXML
    private Text customer_address_2;
    
    @FXML
    private Button notes_btn;
    @FXML
    private Button cash_btn;
    @FXML
    private Button credit_btn;
    @FXML
    private Button check_btn;
    
    @FXML
    private Text subtotal_text;
    @FXML
    private Text taxes_text;
    @FXML
    private Text fees_text;
    @FXML
    private Text total_text;
    
    
    //Menu frame
    @FXML
    private Text title_text;
    @FXML
    private Button menu_back_btn;
    @FXML
    private Button menu_done_btn;
    
    
    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    
    
    public MainController() {}
    
    @FXML
    private void initialize() {
    	//Updates the time every second
    	Timer timer = new Timer("Display Time");
    	timer.scheduleAtFixedRate(new TimeUpdater(time_text), 1000, 1000);
    	
    	searchQuery = "";
    	customerSearchInput = new PhoneInput(customer_search_input);
    	
    	orderNum = 1;
    	order = new Order(orderNum);
    	order_num_text.setText(orderNum+"");
    	order_type_text.setText("Pickup");
    	customer_name.setText("");
    	customer_phone.setText("");
    	customer_address_1.setText("");
    	customer_address_2.setText("");
    }
    
    
    @FXML
    private void updateSearchInput() {
    	searchQuery = customerSearchInput.update();
    }
    
    @FXML
    private void selectOrderType() {
    	ToggleButton selected = (ToggleButton)order_type.getSelectedToggle();
    	if(selected == pickup_btn) {
    		pickup_btn.setDisable(true);
    		delivery_btn.setDisable(false);
    		order_type_text.setText("Pickup");
    	} else if(selected == delivery_btn) {
    		delivery_btn.setDisable(true);
    		pickup_btn.setDisable(false);
    		order_type_text.setText("Delivery");
    	}
    }
    
    @FXML
    private void selectNavBarItem() {
    	ToggleButton selected = (ToggleButton)nav_bar_main.getSelectedToggle();
    	
    	title_text.setText(selected.getText());
    	for(Toggle toggle : nav_bar_main.getToggles()) {
    		ToggleButton btn = (ToggleButton) toggle;
    		btn.setDisable(btn == selected);
    	}
    }
    
    @FXML
    private void selectMenuItem() {
    	Button selected = null;
    	System.out.println();
    }
    
    
    
    @FXML
    private void logout() {
    	System.exit(0);
    }
}

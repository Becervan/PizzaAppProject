package res.layout;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import core.Customer;
import core.CustomerDatabase;
import core.Order;
import core.Resource;
import core.TimeUpdater;
import gui.components.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MainController {
	private CustomerDatabase customerDatabase;
	private CustomerInfo customerInfo;
	private PhoneInput customerSearchInput;
	private OrderInfo orderInfo;
	
	private NavBar navbar;
	private Menu menu;
	private Order order;
    
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
    private Button menu_btn;
    @FXML
    private Button coupons_btn;
    @FXML
    private Button orders_btn;
    @FXML
    private Button functions_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Text time_text;
    
    
    //Orders pane
    @FXML
    private Text order_num_text;
    @FXML
    private Text order_type_text;
    @FXML
    private Text subtotal_text;
    @FXML
    private Text taxes_text;
    @FXML
    private Text fees_text;
    @FXML
    private Text total_text;
    
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
    
    
    //Menu frame
    @FXML
    private GridPane menu_nav_grid;
    @FXML
    private Text title_text;
    @FXML
    private Button menu_back_btn;
    @FXML
    private Button menu_done_btn;
    @FXML
    private StackPane menu_stack_pane; 
    @FXML
    private GridPane menu_btn_grid;

    
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
		
    	customerDatabase = new CustomerDatabase(new Resource("res/json/customers.json").getPath());
    	customerSearchInput = new PhoneInput(customer_search_input);
    	customerInfo = new CustomerInfo(customer_name, customer_phone, customer_address_1, customer_address_2);
    	orderInfo = new OrderInfo(order_num_text, order_type_text, subtotal_text, taxes_text, fees_text, total_text);
    	
    	menu = new Menu(title_text, menu_nav_grid, menu_btn_grid, menu_back_btn, menu_done_btn);
    	navbar = new NavBar(menu, new Button[] {menu_btn, coupons_btn, orders_btn, functions_btn}, logout_btn);
    	order = new Order(1);
    }
    
    
    @FXML
    private void selectOrderType() {
    	ToggleButton selected = (ToggleButton)order_type.getSelectedToggle();
    	if(selected == pickup_btn) {
    		pickup_btn.setDisable(true);
    		delivery_btn.setDisable(false);
    		orderInfo.setOrderType("Pickup");
    	} else if(selected == delivery_btn) {
    		delivery_btn.setDisable(true);
    		pickup_btn.setDisable(false);
    		orderInfo.setOrderType("Delivery");
    	}
    }
    
    @FXML
    private void updateSearchInput() {
    	String searchQuery = customerSearchInput.update();
    	if(searchQuery.length() >= 6) {
    		Customer[] searchResults = customerDatabase.search(searchQuery);
    		for(Customer x : searchResults) System.out.println(x);
    	}
    }
    
    private void setCustomer(Customer customer) {
    	customerInfo.update(customer);
    }
}

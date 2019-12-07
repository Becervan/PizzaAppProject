package res.layout;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import core.Customer;
import core.CustomerDatabase;
import core.Order;
import core.TimeUpdater;
import gui.components.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {
	
	private CustomerDatabase customerDatabase;
	private CustomerInfo customerInfo;
	private PhoneInput customerSearchInput;
	private OrderInfo orderInfo;
	
	private NavBar navbar;
	private Menu menu;
    
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
    @FXML
    private ListView<String> search_results;
    
    //Nav bar
    @FXML
    private Button menu_btn;
    @FXML
    private Button coupons_btn;
    @FXML
    private Button functions_btn;
    @FXML
    private Button orders_btn;
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
    @FXML
    private ListView<String> order_item_list;
    
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
    private GridPane menu_level_1_2_container;
    @FXML
    private VBox menu_level_3_container; //Contains the following:
    
    @FXML
    private GridPane menu_level_3_options_grid; //Contains the following:
    @FXML
    private GridPane menu_level_3_size_grid;
    @FXML
    private GridPane menu_level_3_crust_grid; //Pizza items only
    @FXML
    private GridPane menu_level_3_toppings_grid; //Pizza items only

    
    
    
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
    	
		customerDatabase = new CustomerDatabase("res/json/customers.json");
    	customerSearchInput = new PhoneInput(customer_search_input);
    	customerInfo = new CustomerInfo(customer_name, customer_phone, customer_address_1, customer_address_2);
    	orderInfo = new OrderInfo(order_num_text, order_type_text, subtotal_text, taxes_text, fees_text, total_text, order_item_list);
    	
    	menu = new Menu(orderInfo, title_text, menu_nav_grid, menu_level_1_2_container, menu_back_btn, menu_done_btn, menu_level_3_container, menu_level_3_options_grid, menu_level_3_size_grid, menu_level_3_crust_grid, menu_level_3_toppings_grid);
    	navbar = new NavBar(menu, new Button[] {menu_btn, coupons_btn, orders_btn, functions_btn}, logout_btn);
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
    	if(searchQuery.length() > 6) {
    		Customer[] searchResults = customerDatabase.search(searchQuery);
    		
    		search_results.getItems().clear();
    		for(Customer x : searchResults)
    			search_results.getItems().add(x.getName()+"\n"+x.getPhone());
    		
    		search_results.setVisible(true);
    		search_results.setOnMouseClicked(e -> setCustomer(searchResults[search_results.getSelectionModel().getSelectedIndex()]));
    	} else search_results.setVisible(false);
    }
    
    private void setCustomer(Customer customer) {
    	search_results.setVisible(false);
		customer_search_input.setDisable(true);
		customerSearchInput.setText(customer.getPhone());
		menu.getOrder().setCustomer(customer);
		customerInfo.update(customer);
    }
}

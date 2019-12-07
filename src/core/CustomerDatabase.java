package core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CustomerDatabase {
	private ClassLoader loader = getClass().getClassLoader();
	private Customer[] customers;
	
	public CustomerDatabase(String fileName) {
		loadCustomers(fileName);
	}
	
	private void loadCustomers(String fileName) {
		try {
			Object root = new JSONParser().parse(new InputStreamReader(loader.getResourceAsStream(fileName)));
			JSONArray list = (JSONArray)root;
			
			customers = new Customer[list.size()];
			for(int i = 0; i < customers.length; i++) {
				JSONArray x = (JSONArray)list.get(i);
				customers[i] = new Customer(i, (String)x.get(0), (String)x.get(1), (String)x.get(2), (String)x.get(3), (String)x.get(4), (String)x.get(5));
			}
			MergeSort.sort(customers);
		} catch(IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Customer[] search(String query) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		for(int i = 0; i < customers.length; i++)
			if(customers[i].getPhone().startsWith(query))
				list.add(customers[i]);	
		
		Customer[] results = new Customer[list.size()];
		for(int i = 0; i < list.size(); i++) {
			results[i] = list.get(i);
		}
		return results;
	}
}

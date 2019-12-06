package gui;

import java.io.IOException;

import core.Resource;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
//import javafx.scene.Cursor;
//import javafx.scene.control.Label;

import javafx.stage.Stage;
//import javafx.stage.Modality;
//import javafx.stage.StageStyle;

public class Main extends Application {
	private static final String SCREEN_TITLE = "The Oven - POS";
	private static final int SCREEN_WIDTH = 1600;
	private static final int SCREEN_HEIGHT = 900;
	
	private FXMLLoader loader;
	
	//Loads and returns the root node from a FXML file
	private VBox loadFXML(Resource resource) throws IOException {
		loader.setLocation(resource.getURL()); //Gets the URL to the FXML file
		return loader.<VBox>load();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		loader = new FXMLLoader();
		VBox root = loadFXML(new Resource("res/layout/main_screen.fxml"));
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		stage.setTitle(SCREEN_TITLE);
		stage.setScene(scene);
		stage.setFullScreen(true); //Sets the stage to fullscreen mode (stage must have a scene)
		stage.show();
	}
	
	//START HERE!
	public static void main(String[] args) {
		Application.launch(args);
	}
}








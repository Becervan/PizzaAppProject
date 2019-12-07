package gui;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
//import javafx.scene.Cursor;
//import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.stage.Modality;
//import javafx.stage.StageStyle;

public class Main extends Application {
	private static final String SCREEN_TITLE = "The Oven - POS";
	private static final int SCREEN_WIDTH = 1600;
	private static final int SCREEN_HEIGHT = 900;
	
	private ClassLoader loader = getClass().getClassLoader();
	
	//Loads and returns the root node from a FXML file
	private VBox loadFXML(URL url) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(url); //Gets the URL to the FXML file
		return fxmlLoader.<VBox>load();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = loadFXML(loader.getResource("res/layout/main_screen.fxml"));
		VBox root2 = loadFXML(loader.getResource("res/layout/login_screen.fxml"));
		
		Stage stage2 = new Stage();
		stage2.setTitle("The Oven - Login");
		stage2.initOwner(stage);
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.setFullScreen(false);
		stage2.setScene(new Scene(root2, 2*SCREEN_WIDTH/3, 19*SCREEN_HEIGHT/20));
		
		
		stage.setTitle(SCREEN_TITLE);
		stage.setFullScreen(true); //Sets the stage to fullscreen mode (stage must have a scene)
		stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
		stage.show();
		stage2.show();
		
	}
	
	//START HERE!
	public static void main(String[] args) {
		launch(args);
	}
}








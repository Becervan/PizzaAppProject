package gui;

import java.io.IOException;
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
	private static final int SCREEN_WIDTH = 1600;
	private static final int SCREEN_HEIGHT = 900;
	
	private FXMLLoader loader;
	
	//Loads and returns the root node from a FXML file
	private VBox loadFXML(String path) throws IOException {
		loader.setLocation(Main.class.getClassLoader().getResource(path)); //Gets the URL to the FXML file
		return loader.<VBox>load();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		loader = new FXMLLoader();
		VBox root = loadFXML("res/layout/main_screen.fxml");
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		stage.setTitle("The Oven - POS");
		stage.setScene(scene);
		stage.setFullScreen(true); //Sets the stage to fullscreen mode (stage must have a scene)
		stage.show();
	}
	
	//START HERE!
	public static void main(String[] args) {
		Application.launch(args);
	}
}























//IGNORE BELOW... We might need these later:

//scene.setCursor(Cursor.NONE); //NONE, DEFAULT, HAND, OPEN_HAND, CLOSED_HAND, WAIT, TEXT, CROSSHAIR, MOVE, H_RESIZE, V_RESIZE
//stage.initStyle(StageStyle.DECORATED); //DECORATED: resize, close, and move features; UNDECORATED: no resize, close, or move features; TRANSPARENT: undecorated with transparent background; UNIFIED: decorated with no border; UTILITY: decorated with minimal features
		
/*
 * Stage stage2 = new Stage();
 * stage2.setScene(new Scene(new Label("Login Screen"), 1280, 720));
 * stage2.initModality(Modality.NONE); //NONE: doesn't block other windows, APPLICATION_MODAL: blocks other windows until stage2 is closed, WINDOW_MODAL: only blocks owner of stage2
 * stage2.initOwner(stage);
 * stage2.show();
 */
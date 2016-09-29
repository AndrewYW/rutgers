package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class SongLib extends Application {
	
	private Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Song Library - Rumzi Tadros, Andrew Wang");
			
			/**
			 * Load layout from FXML file
			 */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SongLib.class.getResource("/view/Lib.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			
			/**
			 * Show scene from layout
			 */
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		File f = new File("/view/songs");
		if(f.exists() && !f.isDirectory()) {
			//method for loading songs to listview
		} else {
		}
		
	}
}

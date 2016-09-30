package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Song;
import view.SongController;

public class SongLib extends Application {

    private Stage primaryStage;

    private ObservableList<Song> songData = FXCollections.observableArrayList();
    
    
    public SongLib() {
    	songData.add(new Song("song 1","art1"));
    	songData.add(new Song("song 2", "art1"));
    }
    
    public ObservableList<Song> getSongData() {
    	return songData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Song Library - Rumzi Tadros & Andrew Wang");

        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(SongLib.class.getResource("/view/Lib.fxml"));
        	AnchorPane rootLayout = (AnchorPane) loader.load();
        	
        	Scene scene = new Scene(rootLayout);
        	primaryStage.setScene(scene);
        	primaryStage.show();
        	
        	SongController controller = loader.getController();
        	controller.setSongLib(this);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
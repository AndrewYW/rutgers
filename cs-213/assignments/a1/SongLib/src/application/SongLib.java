package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Song;
import view.LibController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class SongLib extends Application {
	
	private Stage primaryStage;
	
	/**
	 * Data as observable list of songs.
	 */
	private ObservableList<Song> songData = FXCollections.observableArrayList();
	
	
	/**
	 * Constructor
	 */
	public SongLib() {
		
		/**
		 * Adding sample data
		 * This is where the arraylist file data needs to be inputted.
		 */
		songData.add(new Song("a", "b"));
		
		
	}
	
	public ObservableList<Song> getSongData() {
		return songData;
	}
	
	
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
			
			LibController controller = loader.getController();
			controller.setSongLib(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

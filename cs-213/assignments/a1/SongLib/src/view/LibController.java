package view;
import java.io.*;
import javafx.collections.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class LibController {
	
	
	
	ListView<String> list;

	private ObservableList<String> obslist;
	
	public void start(Stage mainstage) {
		
		obslist = FXCollections.observableArrayList();
	}
	
	
	
}

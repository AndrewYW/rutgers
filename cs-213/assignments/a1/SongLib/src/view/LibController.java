package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Song;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import application.SongLib;

public class LibController {
	
	@FXML
	private TableView<Song> songTable;
	@FXML
	private TableColumn<Song, String> titleColumn;
	@FXML
	private TableColumn<Song, String> artistColumn;
	
	@FXML
	private Label titleLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;
	@FXML
	private Label yearLabel;
	@FXML
	private Label errorMessage;
	
	private SongLib songLib;
	
	public LibController() {
		
	}
	
	
}

package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.SongLib;
import model.Song;

public class SongController {

	@FXML
	private TableView<Song> songTable;
	@FXML 
	TableColumn<Song, String> songColumn;
	
	@FXML
	private Label titleLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;
	@FXML
	private Label yearLabel;
	
	
	private SongLib songLib;
	
	public SongController() {
	}
	
	@FXML
	private void initialize() {
		songColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		
		
		songTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSongDetails(newValue));  //null Pointer Exception here
	}
	

	
	private void showSongDetails(Song song) {
		if (song != null) {
			titleLabel.setText(song.getTitle());
			artistLabel.setText(song.getArtist());			//Null pointer exception here
			albumLabel.setText(song.getAlbum());
			yearLabel.setText(Integer.toString(song.getYear()));
		} else {
			titleLabel.setText("null");
			artistLabel.setText("null");
			albumLabel.setText("null");
			yearLabel.setText("null");
		}
	}
	
	public void setSongLib(SongLib songLib) {
		this.songLib = songLib;
		
		songTable.setItems(songLib.getSongData());
	}
}

package view;

import javafx.fxml.FXML;
import model.Song;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import application.SongLib;

public class LibController {
	
	@FXML
	private ListView<Song> songList;
	
	@FXML
	private Label titleLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;
	@FXML
	private Label yearLabel;
	
	//Reference to main application (SongLib)
	private SongLib songLib;
	
	public LibController() {
		
	}
	
	
	/**
	 * Initializes controller class.
	 */
	@FXML
	private void initialize() {
		songList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSongDetails(newValue));
	}
	
	private void showSongDetails(Song song) {
		if(song != null) {
			titleLabel.setText(song.getTitle());
			artistLabel.setText(song.getArtist());
			albumLabel.setText(song.getAlbum());
			yearLabel.setText(Integer.toString(song.getYear()));
		}
	}
	
	public void setSongLib(SongLib songLib) {
		this.songLib = songLib;
		
		// Add list info to the listview
		songList.setItems(songLib.getSongData());
	}
}

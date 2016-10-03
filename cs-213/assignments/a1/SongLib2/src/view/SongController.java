package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Song;

public class SongController {
	
	@FXML
	private ListView<Song> songList;
	@FXML
	private TextField titleAddField;
	@FXML
	private TextField titleEditField;
	@FXML
	private TextField artistAddField;
	@FXML
	private TextField artistEditField;
	@FXML
	private TextField albumAddField;
	@FXML
	private TextField albumEditField;
	@FXML
	private TextField yearAddField;
	@FXML
	private TextField yearEditField;
	
	
	private ObservableList<Song> obsList;
	
	
	public void start(Stage primaryStage) {
		
		//obsList = get from file
		if(!obsList.isEmpty()) {
			songList.getSelectionModel().selectFirst();
		}
		showSongDetails();
		
		//Set listener for selection changes
		songList.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldValue, newValue) -> showSongDetails());
			
		
		//primaryStage.setOnCloseRequest(event -> )			Write to file here
		
	}
	
	/**
	 * Used to check if input of year fields is an int or not.
	 * @param string
	 * @return boolean
	 */
	public boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	
	@FXML
	private void handleDelete() {
		if(obsList.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("Nothing to delete");
			alert.setContentText("Song list is empty - nothing to delete.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("WARNING");
			alert.setHeaderText("You are about to delete the selected song");
			alert.setContentText("Are you sure?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				int selectedIndex = songList.getSelectionModel().getSelectedIndex();
				obsList.remove(selectedIndex);
				
				if(obsList.isEmpty()) {			//Removing the only item in the list
					titleEditField.setText("");
					artistEditField.setText("");
					albumEditField.setText("");
					yearEditField.setText("");
				} else if (selectedIndex == obsList.size()-1) {
					songList.getSelectionModel().select(selectedIndex--);
				} else {
					songList.getSelectionModel().select(selectedIndex++);
				}
			} else {
				return;
			}
		}
	}
	
	@FXML
	private void handleAdd() {
		if(artistAddField.getText() == "" || titleAddField.getText() == "") {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("Missing input");
			alert.setContentText("Title and Artist input must not be empty");
			alert.showAndWait();
		} else if (1 ==1) {			//Scan through and check for duplicate
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("Same title/artist combination already exists in file");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("WARNING");
			alert.setHeaderText("Adding a new song");
			alert.setContentText("You are about to add a new song: \n" + titleAddField.getText() + "by: " 
									+ artistAddField.getText() + "\nAre you sure?");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				//Add method goes here
			} else {
				showSongDetails();
			}
		}
	}
	
	@FXML
	private void handleEdit() {
		if(1 == 1) {
			
		} 
	}
	
	private void showSongDetails() {
		if(songList.getSelectionModel().getSelectedIndex() < 0) {
			return;
		}
		
		Song song = songList.getSelectionModel().getSelectedItem();
		titleEditField.setText(song.getTitle());
		artistEditField.setText(song.getArtist());
		albumEditField.setText(song.getAlbum());
		yearEditField.setText(String.valueOf(song.getYear()));
	}

	private boolean isUnique(Song selectedSong) {
		for (Song s : obsList) {
			if (s.compareTo(selectedSong) == 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
}

/**
 * Andrew Wang
 * Rumzi Tadros
 * 
 * Song Object using properties to make use of binding synchronization.
 */
package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song {
	
	private final StringProperty title;
	private final StringProperty artist;
	private final StringProperty album;
	private final IntegerProperty year;
	
	public Song() {
		this(null, null);
	}
	
	/**
	 *  Song Constructor
	 * @param title
	 * @param artist
	 */
	public Song(String title, String artist) {
		this.title = new SimpleStringProperty(title);
		this.artist = new SimpleStringProperty(artist);
		
		this.album = new SimpleStringProperty("");
		this.year = new SimpleIntegerProperty(0000);
	}
	
	/**
	 * Song title methods
	 */
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public StringProperty titleProperty() {
		return title;
	}
	
	/**
	 * Song artist methods
	 */
	public String getArtist() {
		return artist.get();
	}
	public void setArtist(String artist) {
		this.artist.set(artist);
	}
	public StringProperty artistProperty() {
		return artist;
	}
	
	/**
	 * Song album methods
	 */
	public String getAlbum() {
		return album.get();
	}
	public void setAlbum(String album) {
		this.album.set(album);
	}
	public StringProperty albumProperty() {
		return album;
	}
	
	/**
	 * Song year methods
	 */
	public int getYear() {
		return year.get();
	}
	public void setYear(int year) {
		this.year.set(year);
	}
	public IntegerProperty yearProperty() {
		return year;
	}
}
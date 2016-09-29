package obj;

public class Song {
	private String title;
	private String artist;
	private String album;
	private String year;
	
	public Song(String title, String artist, String album, String year) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getTitle() {
		return this.title;
	}
	public String getArtist() {
		return this.artist;
	}
	public String getAlbum() {
		return this.album;
	}
	public String getYear() {
		return this.year;
	}
	
	
}

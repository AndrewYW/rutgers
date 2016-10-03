package model;
public class Song implements Comparable<Song>{
	private String title;
	private String artist;
	private String album;
	private int year;
	
	public Song(String title, String artist, String album, int year) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public Song(String title, String artist) {
		this(title, artist, "", 0);
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
	public int getYear() {
		return this.year;
	}

	
	
	@Override
	
	public int compareTo(Song o) {
		if(this.title.trim().equalsIgnoreCase(o.getTitle().trim())) {
			if(this.artist.trim().equalsIgnoreCase(o.getArtist().trim())) {
				return 0;		//title and artist match
			}
		} else {
			return 1;
		}
		return 1;
	}

	
	
	
}

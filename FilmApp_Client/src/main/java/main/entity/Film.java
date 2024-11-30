package main.entity;

import java.util.Objects;

public class Film {

	private int id;
	private String title;
	private double rating;
	private Director director;

	public Film(int id, String title, double rating, Director director) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.director = director;
	}

	public Film() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
	    return String.format(
	        "\n----- Film Details -----\n" +
	        "ID: %d\n" +
	        "Title: %s\n" +
	        "Rating: %.1f\n" +
	        "Director: %s\n" +
	        "------------------------",
	        id, title, rating, director.getName()
	    );
	}


}

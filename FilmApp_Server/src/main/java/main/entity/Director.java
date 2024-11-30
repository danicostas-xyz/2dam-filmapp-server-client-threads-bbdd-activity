package main.entity;

import java.util.List;
import java.util.Objects;

public class Director {

	private int id;
	private String name;
	private List<Film> filmList;

	public Director(int id, String name, List<Film> filmList) {
		super();
		this.id = id;
		this.name = name;
		this.filmList = filmList;
	}

	public Director() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Film> getFilmList() {
		return filmList;
	}

	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}

	@Override
	public String toString() {
	    
	    StringBuilder list = new StringBuilder();
	    
	    for (Film film : filmList) {
	        list.append("\t- " + film).append("\n");  
	    }
	    
	    return String.format(
	        "\n----- Director Details -----\n" +
	        "ID: %d\n" +
	        "Name: %s\n" +
	        "Films Directed:\n%s" +
	        "----------------------------",
	        id, name, list.toString()
	    );
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
		Director other = (Director) obj;
		return id == other.id;
	}

}

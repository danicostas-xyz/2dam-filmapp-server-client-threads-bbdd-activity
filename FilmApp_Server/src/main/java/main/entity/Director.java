package main.entity;

import java.util.List;
import java.util.Objects;

public class Director {

	private int id;
	private String name;
	private String primer_apellido;
	private String segundo_apellido;
	private List<Film> filmList;

	public Director(int id, String name, String primer_apellido, String segundo_apellido, List<Film> filmList) {
		super();
		this.id = id;
		this.name = name;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
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

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public List<Film> getFilmList() {
		return filmList;
	}

	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}

	@Override
	public String toString() {
		return "Director [id=" + id + ", name=" + name + ", primer_apellido=" + primer_apellido + ", segundo_apellido="
				+ segundo_apellido + ", filmList=" + filmList + "]";
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

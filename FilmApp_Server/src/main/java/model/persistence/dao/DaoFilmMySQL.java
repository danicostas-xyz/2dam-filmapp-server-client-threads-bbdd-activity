package model.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.AppConfig;
import main.entity.Director;
import main.entity.Film;
import model.persistence.interfaces.DaoFilm;

public class DaoFilmMySQL implements DaoFilm {

	public static DaoFilmMySQL daoFilm;

	private DaoFilmMySQL() {

	}

	public static DaoFilmMySQL getInstance() {
		return (daoFilm == null) ? daoFilm = new DaoFilmMySQL() : daoFilm;
	}

	private String url = AppConfig.getInstance().getProperty("url");
	private String user = AppConfig.getInstance().getProperty("user");
	private String pass = AppConfig.getInstance().getProperty("pass");

	@Override
	public Integer createFilm(Film f, Director d) {

		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "INSERT INTO film (title, id_director, rating) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, f.getTitle());
			ps.setInt(2, d.getId());
			ps.setDouble(3, f.getRating());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Film getFilmById(int filmID) {

		Film f = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "SELECT * FROM film WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, filmID);
			f = resultSetHandler(ps).get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
	}

	@Override
	public Film getFilmByTitle(String filmTitle) {

		Film f = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "SELECT * FROM film WHERE title=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, filmTitle);
			f = resultSetHandler(ps).get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
	}

	@Override
	public List<Film> getFilmsByDirectorId(int directorID) {

		List<Film> filmList = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "SELECT * FROM film WHERE id_director=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, directorID);
			filmList = resultSetHandler(ps);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filmList;
	}

	@Override
	public Integer updateFilmById(Film f) {

		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "UPDATE film SET title=?, id_director=?, rating=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, f.getTitle());
			ps.setInt(2, f.getDirector().getId());
			ps.setDouble(3, f.getRating());
			ps.setInt(4, f.getId());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer deleteFilmById(int filmID) {

		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "DELETE FROM film WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, filmID);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// TODO Private Method Documentation
	private List<Film> resultSetHandler(PreparedStatement ps) {

		List<Film> filmList = new ArrayList<>();
		Film film = null;

		try {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDirector(DaoDirectorMySQL.getInstance().getDirectorById(rs.getInt(3)));

				filmList.add(film);
			}

		} catch (SQLException e) {
			filmList = null;
			e.printStackTrace();
		}

		if (filmList.size() == 0)
			filmList.add(null);

		return filmList;
	}
}

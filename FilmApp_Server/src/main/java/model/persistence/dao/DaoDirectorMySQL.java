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
import model.persistence.interfaces.DaoDirector;

public class DaoDirectorMySQL implements DaoDirector {

	public static DaoDirectorMySQL daoDirector;

	private DaoDirectorMySQL() {
	}

	public static DaoDirectorMySQL getInstance() {
		return (daoDirector == null) ? daoDirector = new DaoDirectorMySQL() : daoDirector;
	}
	
	private String url = AppConfig.getInstance().getProperty("url");
	private String user = AppConfig.getInstance().getProperty("user");
	private String pass = AppConfig.getInstance().getProperty("pass");

	@Override
	public Integer createDirector(Director d) {
		
		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "INSERT INTO director (name) VALUES (?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getName());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Director getDirectorById(int directorID) {

		Director d = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "SELECT * FROM director WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, directorID);
			d = resultSetHandler(ps).get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d;
	}

	@Override
	public Director getDirectorByName(String directorName) {

		Director d = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "SELECT * FROM director WHERE name=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, directorName);
			d = resultSetHandler(ps).get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d;
	}

	@Override
	public Integer updateDirectorById(Director d) {

		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "UPDATE director SET name=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getName());
			ps.setInt(2, d.getId());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer deleteDirectorById(int directorID) {

		Integer result = null;

		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

			String query = "DELETE FROM director WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, directorID);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// TODO Private Method Documentation
	private List<Director> resultSetHandler(PreparedStatement ps) {

		List<Director> directorList = new ArrayList<>();
		Director director = null;

		try {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				director = new Director();
				director.setId(rs.getInt(1));
				director.setName(rs.getString(2));
				director.setFilmList(DaoFilmMySQL.getInstance().getFilmsByDirectorId(rs.getInt(1)));

				directorList.add(director);
			}

		} catch (SQLException e) {
			directorList = null;
			e.printStackTrace();
		}

		if (directorList.size() == 0)
			directorList.add(null);

		return directorList;
	}

}

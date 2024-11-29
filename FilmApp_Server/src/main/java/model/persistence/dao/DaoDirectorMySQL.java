package model.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.entity.Director;
import model.persistence.interfaces.DaoDirector;

public class DaoDirectorMySQL implements DaoDirector {

	public static DaoDirectorMySQL daoDirector;

	private DaoDirectorMySQL() {
	}

	public static DaoDirectorMySQL getInstance() {
		return (daoDirector == null) ? daoDirector = new DaoDirectorMySQL() : daoDirector;
	}

	@Override
	public Integer createDirector(Director d) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director getDirectorById(int directorID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director getDirectorByName(String directorName) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateDirectorById(int directorID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteDirectorById(int directorID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
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

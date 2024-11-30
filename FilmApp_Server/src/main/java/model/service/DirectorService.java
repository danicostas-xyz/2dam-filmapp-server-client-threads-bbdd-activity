package model.service;

import main.entity.Director;
import model.persistence.dao.DaoDirectorMySQL;

public class DirectorService {

	private DaoDirectorMySQL dao;

	public static DirectorService directorService;

	private DirectorService() {
	}

	public static DirectorService getInstance() {
		return (directorService == null) ? directorService = new DirectorService() : directorService;
	}

	/**
	 * Crea un director en la persistencia.
	 * 
	 * @param f el objeto Director a añadir a la persistencia.
	 * @return 1 en caso de que se haya añadido correctamente, 0 en caso de que no
	 *         se haya añadido por error en algún dato, null en caso de que haya
	 *         sucedido una I/O o SQL Exception
	 */
	public Integer createDirector(Director d) {
		return dao.createDirector(d);
	}

	/**
	 * Recupera un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a buscar.
	 * @return Si el ID pasado por parámetro coincide con algún director, devuelve
	 *         un objeto Director con dicho director. Si no hay coincidencias,
	 *         devuelve null.
	 */
	public Director getDirectorById(int directorID) {
		return dao.getDirectorById(directorID);
	}

	/**
	 * Recupera un director de la persistencia buscando por el nombre del director.
	 * 
	 * @param directorName, el nombre del director a buscar.
	 * @return Si el nombre pasado por parámetro coincide con algún director,
	 *         devuelve un objeto Director con dicho director. Si no hay
	 *         coincidencias, devuelve null.
	 */
	public Director getDirectorByName(String directorName) {
		return dao.getDirectorByName(directorName);
	}

	/**
	 * Modifica un director de la persistencia buscando por el ID del director.
	 * 
	 * @param d, el Director a modificar.
	 * @return 1 en caso de que se haya modificado correctamente, 0 en caso de que
	 *         no se haya modificado por no encontrarse el director, null en caso de
	 *         que haya sucedido una I/O o SQL Exception
	 */
	public Integer updateDirectorById(Director d) {
		return updateDirectorById(d);
	}

	/**
	 * Elimina un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a eliminar.
	 * @return 1 en caso de que se haya eliminado correctamente, 0 en caso de que no
	 *         se haya eliminado por no encontrarse el director, null en caso de que
	 *         haya sucedido una I/O o SQL Exception
	 */
	public Integer deleteDirectorById(int directorID) {
		return deleteDirectorById(directorID);
	}

}

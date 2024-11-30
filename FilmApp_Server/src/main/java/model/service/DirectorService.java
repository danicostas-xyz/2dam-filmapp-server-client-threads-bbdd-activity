package model.service;

import main.entity.Director;
import model.persistence.dao.DaoDirectorMySQL;
import model.persistence.interfaces.DaoDirector;

public class DirectorService {

	private DaoDirector dao;

	public static DirectorService directorService;

	private DirectorService() {
		this.dao = DaoDirectorMySQL.getInstance();
	}

	public static synchronized DirectorService getInstance() {
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
	public synchronized Integer createDirector(Director d) {
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
	public synchronized Director getDirectorById(int directorID) {
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
	public synchronized Director getDirectorByName(String directorName) {
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
	public synchronized Integer updateDirectorById(Director d) {
		return dao.updateDirectorById(d);
	}

	/**
	 * Elimina un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a eliminar.
	 * @return 1 en caso de que se haya eliminado correctamente, 0 en caso de que no
	 *         se haya eliminado por no encontrarse el director, null en caso de que
	 *         haya sucedido una I/O o SQL Exception
	 */
	public synchronized Integer deleteDirectorById(int directorID) {
		return dao.deleteDirectorById(directorID);
	}

}

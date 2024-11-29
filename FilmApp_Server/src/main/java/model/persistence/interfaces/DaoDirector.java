package model.persistence.interfaces;

import java.util.List;

import main.entity.Director;
import main.entity.Film;

public interface DaoDirector {

	/**
	 * Crea un director en la persistencia.
	 * 
	 * @param f el objeto Director a añadir a la persistencia.
	 * @return 1 en caso de que se haya añadido correctamente, 0 en caso de que no
	 *         se haya añadido por error en algún dato, null en caso de que haya
	 *         sucedido una I/O o SQL Exception
	 */
	Integer createDirector(Director d);

	/**
	 * Recupera un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a buscar.
	 * @return Si el ID pasado por parámetro coincide con algún director, devuelve
	 *         un objeto Director con dicho director. Si no hay coincidencias,
	 *         devuelve null.
	 */
	Director getDirectorById(int directorID);

	/**
	 * Recupera un director de la persistencia buscando por el nombre del director.
	 * 
	 * @param directorName, el nombre del director a buscar.
	 * @return Si el nombre pasado por parámetro coincide con algún director,
	 *         devuelve un objeto Director con dicho director. Si no hay
	 *         coincidencias, devuelve null.
	 */
	Director getDirectorByName(String directorName);

	/**
	 * Modifica un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a modificar.
	 * @return 1 en caso de que se haya modificado correctamente, 0 en caso de que
	 *         no se haya modificado por no encontrarse el director, null en caso de
	 *         que haya sucedido una I/O o SQL Exception
	 */
	Integer updateDirectorById(int directorID);

	/**
	 * Elimina un director de la persistencia buscando por el ID del director.
	 * 
	 * @param directorID, el ID del director a eliminar.
	 * @return 1 en caso de que se haya eliminado correctamente, 0 en caso de que no
	 *         se haya eliminado por no encontrarse el director, null en caso de que
	 *         haya sucedido una I/O o SQL Exception
	 */
	Integer deleteDirectorById(int directorID);

}

package model.persistence.interfaces;

import main.entity.Film;

public interface DaoFilm {

	/**
	 * Crea una película en la persistencia.
	 * 
	 * @param f el objeto Film a añadir a la persistencia.
	 * @return 1 en caso de que se haya añadido correctamente, 0 en caso de que no
	 *         se haya añadido por error en algún dato, null en caso de que haya
	 *         sucedido una I/O o SQL Exception
	 */
	Integer createFilm(Film f);

	/**
	 * Recupera una película de la persistencia buscando por el ID de la película.
	 * 
	 * @param filmID, el ID de la película a buscar.
	 * @return Si el ID pasado por parámetro coincide con alguna película, devuelve
	 *         un objeto Film con dicha película. Si no hay coincidencias, devuelve
	 *         null.
	 */
	Film getFilmById(int filmID);

	/**
	 * Recupera una película de la persistencia buscando por el título de la
	 * película.
	 * 
	 * @param filmTitle, el título de la película a buscar.
	 * @return Si el título pasado por parámetro coincide con alguna película,
	 *         devuelve un objeto Film con dicha película. Si no hay coincidencias,
	 *         devuelve null.
	 */
	Film getFilmByTitle(String filmTitle);

	/**
	 * Modifica una película de la persistencia buscando por el ID de la película.
	 * 
	 * @param filmID, el ID de la película a modificar.
	 * @return 1 en caso de que se haya modificado correctamente, 0 en caso de que
	 *         no se haya modificado por no encontrarse la película, null en caso de
	 *         que haya sucedido una I/O o SQL Exception
	 */
	Integer updateFilmById(int filmID);

	/**
	 * Elimina una película de la persistencia buscando por el ID de la película.
	 * 
	 * @param filmID, el ID de la película a eliminar.
	 * @return 1 en caso de que se haya eliminado correctamente, 0 en caso de que
	 *         no se haya eliminado por no encontrarse la película, null en caso de
	 *         que haya sucedido una I/O o SQL Exception
	 */
	Integer deleteFilmById(int filmID);

}

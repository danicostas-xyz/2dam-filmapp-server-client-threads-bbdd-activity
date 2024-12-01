package model.service;

import java.util.ArrayList;
import com.google.gson.Gson;
import main.entity.Director;
import main.entity.Film;

public class InputOutputDataService {

	private Gson gson;

	public static InputOutputDataService inOutDataService;

	private InputOutputDataService() {
		gson = new Gson();
	}

	public static InputOutputDataService getInstance() {
		return (inOutDataService == null) ? inOutDataService = new InputOutputDataService() : inOutDataService;
	}

	public String requestFormatter(int requestChoice, Object requestObject) {
		Film film = null;
		Director director = null;
		int filmID = 0;
		int directorID = 0;
		String jsonFormat = "";

		switch (requestChoice) {
		case 0:
			// Create Film
			film = (Film) requestObject;
			jsonFormat = gson.toJson(film);
			return requestChoice + "_" + jsonFormat;
		case 1:
			// Get Film By ID
			filmID = (Integer) requestObject;
			return requestChoice + "_" + filmID;
		case 2:
			// Get Film By Title
			String filmTitle = (String) requestObject;
			return requestChoice + "_" + filmTitle;
		case 3:
			// Update Film By ID
			film = (Film) requestObject;
			jsonFormat = gson.toJson(film);
			return requestChoice + "_" + jsonFormat;
		case 4:
			// Get Films By Director ID
			directorID = (Integer) requestObject;
			return requestChoice + "_" + directorID;
		case 5:
			// Delete Film By ID
			filmID = (Integer) requestObject;
			return requestChoice + "_" + filmID;
		case 7:
			// Create Director
			director = (Director) requestObject;
			jsonFormat = gson.toJson(director);
			return requestChoice + "_" + jsonFormat;
		case 8:
			// Get Director By ID
			directorID = (Integer) requestObject;
			return requestChoice + "_" + directorID;
		case 9:
			// Get Director By Name
			String directorName = (String) requestObject;
			return requestChoice + "_" + directorName;
		case 10:
			// Update Director By ID
			director = (Director) requestObject;
			jsonFormat = gson.toJson(director);
			return requestChoice + "_" + jsonFormat;
		case 11:
			// Delete Director By ID
			directorID = (Integer) requestObject;
			return requestChoice + "_" + directorID;
		}

		return null;
	}

	/**
	 * Formatea la respuesta del servidor de String (JSON o String puro) a lo que
	 * sea en cada caso.
	 * 
	 * @param requestChoice
	 * @param response
	 * @return
	 */
	public Object responseFormatter(int requestChoice, String response) {

		switch (requestChoice) {
		case 1:
			// Get Film By ID (Returns Film)
			return gson.fromJson(response, Film.class);
		case 2:
			// Get Film By Title (Returns Film)
			return gson.fromJson(response, Film.class);
		case 0:
			// Create Film (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 3:
			// Update Film By ID (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 5:
			// Delete Film By ID (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 7:
			// Create Director (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 10:
			// Update Director By ID (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 11:
			// Delete Director By ID (Returns Integer)
			return gson.fromJson(response, Integer.class);
		case 4:
			// Get List of Films By Director ID (Returns ArrayList of Film)
			return gson.fromJson(response, ArrayList.class);
		case 8:
			// Get Director By ID (Returns Director)
			return gson.fromJson(response, Director.class);
		case 9:
			// Get Director By Name (Returns Director)
			return gson.fromJson(response, Director.class);
		}
		return null;
	}

}

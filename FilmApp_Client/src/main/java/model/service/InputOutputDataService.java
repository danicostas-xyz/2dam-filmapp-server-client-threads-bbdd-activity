package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.google.gson.Gson;

import main.entity.Director;
import main.entity.Film;
import model.persistence.dao.DaoServerConnection;

public class InputOutputDataService {
	
	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Gson gson;
	private DaoServerConnection dao;
	
	public static InputOutputDataService inOutDataService;

	private InputOutputDataService() {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
		gson = new Gson();
		dao = DaoServerConnection.getInstance();
	}

	public static InputOutputDataService getInstance() {
		return (inOutDataService == null) ? inOutDataService = new InputOutputDataService() : inOutDataService;
	}

	public void conexion() {

		boolean connectionStatus = true;

		try (Socket socketToServer = new Socket();) {
			
			socketToServer.connect(address);

			PrintStream ps = new PrintStream(socketToServer.getOutputStream());
			InputStreamReader isr = new InputStreamReader(socketToServer.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			while (connectionStatus) {

				int requestChoice = 0;
				String requestObject = "";
				
				// Output message
				Director d = new Director();
				d.setName("David Lynch");
				ps.println(requestChoice + "_" + requestObject);

				// Input message
				String entrada = br.readLine();

				if (entrada.equalsIgnoreCase("OK")) {
					System.out.println("Server cierra conexión");
					connectionStatus = false;
					socketToServer.close();
				} else {
					Film f = (Film) gson.fromJson(entrada, Film.class);
					System.out.println(f);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String choiceHandler(String requestChoice, Object requestObject) {
		
		Film film = null;
		Director director = null;
		int filmID = 0;
		int directorID = 0;

		switch (Integer.parseInt(requestChoice)) {
		case 0:
			// Create Film
			film = gson.fromJson(requestObject, Film.class);
			response = gson.toJson(filmService.createFilm(film));
			break;
		case 1:
			// Get Film By ID
			filmID = Integer.parseInt(requestObject);
			response = gson.toJson(filmService.getFilmById(filmID));
			break;
		case 2:
			// Get Film By Title
			String filmTitle = requestObject;
			response = gson.toJson(filmService.getFilmByTitle(filmTitle));
			break;
		case 3:
			// Update Film By ID
			film = gson.fromJson(requestObject, Film.class);
			response = gson.toJson(filmService.updateFilmById(film));
			break;
		case 4:
			// Get Films By Director ID
			directorID = Integer.parseInt(requestObject);
			response = gson.toJson(filmService.getFilmsByDirectorId(directorID));
			break;
		case 5:
			// Delete Film By ID
			filmID = Integer.parseInt(requestObject);
			response = gson.toJson(filmService.deleteFilmById(filmID));
			break;
		case 7:
			// Create Director
			director = gson.fromJson(requestObject, Director.class);
			response = gson.toJson(directorService.createDirector(director));
			break;
		case 8:
			// Get Director By ID
			directorID = Integer.parseInt(requestObject);
			response = gson.toJson(directorService.getDirectorById(directorID));
			break;
		case 9:
			// Get Director By Name
			String directorName = requestObject;
			response = gson.toJson(directorService.getDirectorByName(directorName));
			break;
		case 10:
			// Update Director By ID
			director = gson.fromJson(requestObject, Director.class);
			response = gson.toJson(directorService.updateDirectorById(director));
			break;
		case 11:
			// Delete Director By ID
			directorID = Integer.parseInt(requestObject);
			response = gson.toJson(directorService.deleteDirectorById(directorID));
			break;
		}

		dao.sendData(requestChoice + "_" + requestObject);
		return requestChoice + "_" + requestObject;
	}
}

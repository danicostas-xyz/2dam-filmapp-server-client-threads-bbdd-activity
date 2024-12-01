package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import main.entity.Director;
import main.entity.Film;

public class ClientManagerService implements Runnable {

	private Socket socketToClient;
	private Thread thread;
	private List<Socket> socketsArray;
	private static int num_cliente;
	private FilmService filmService;
	private DirectorService directorService;
	private Gson gson;

	public ClientManagerService(Socket socketToClient, List<Socket> socketsArray) {
		this.socketToClient = socketToClient;
		this.socketsArray = socketsArray;
		this.filmService = FilmService.getInstance();
		this.directorService = DirectorService.getInstance();
		this.gson = new Gson();
		thread = new Thread(this, "User " + num_cliente++);
		thread.start();
	}

	@Override
	public void run() {

		boolean connectionStatus = true;
		String response = "";

		try (InputStreamReader isr = new InputStreamReader(socketToClient.getInputStream());
				PrintStream ps = new PrintStream(socketToClient.getOutputStream());
				BufferedReader br = new BufferedReader(isr);) {

			while (connectionStatus) {

				// Input message ("X_OBJECT") / Request from client.

				String requestInput = br.readLine();
				String requestChoice = requestInput.split("_")[0];
				String requestObject = requestInput.split("_")[1];

				System.out.println("User [" + Thread.currentThread().getName() + "] requested option: " + requestChoice);
				System.out.println("Request data: " + requestObject);

				// Output message / Response to client.

				if (requestChoice.equalsIgnoreCase("END")) {
					System.out.println("Connection ended by user request."); // Log message in server console.
					connectionStatus = false; // Sets connectionStatus to false to exit while loop.
					ps.println("OK"); // Sends signal to client to close socket.
					socketToClient.close(); // Closes socket in server.
					socketsArray.remove(this.socketToClient); // Deletes THIS socket from the Server.java socket list
					System.out.println("- Users Online: " + socketsArray.size()); // Shows the number of clients (THIS
																					// one is no longer active).
				} else {
					response = choiceHandler(response, ps, requestChoice, requestObject);
					System.out.println("Response to User [" + Thread.currentThread().getName() + "] -> " + response);
				}
			}

		} catch (IOException e) {
			response = "ERROR: Connection error. Try again later.";
			e.printStackTrace();
		} catch (NumberFormatException e) {
			response = "ERROR: Invalid numeric input.";
		} catch (JsonSyntaxException e) {
			response = "ERROR: Malformed JSON.";
		} catch (Exception e) {
			response = "ERROR: An unexpected error occurred.";
		}
	}

	private String choiceHandler(String response, PrintStream ps, String requestChoice, String requestObject) {
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

		ps.println(response); // Sends the response to the client.
		return response;
	}
}

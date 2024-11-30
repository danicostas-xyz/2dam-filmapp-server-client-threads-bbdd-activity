package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

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
		thread = new Thread(this, "Cliente " + num_cliente++);
		thread.start();
	}

	@Override
	public void run() {

		boolean connectionStatus = true;

		try (InputStreamReader isr = new InputStreamReader(socketToClient.getInputStream());
				PrintStream ps = new PrintStream(socketToClient.getOutputStream());
				BufferedReader br = new BufferedReader(isr);) {

			while (connectionStatus) {

				String response = "";

				// Input message
				String inputMessage = br.readLine();
				String clientChoice = inputMessage.split("_")[0];
				Object objectSend = gson.fromJson(inputMessage.split("_")[1], Object.class);
				System.out.println("Mensaje del cliente " + Thread.currentThread().getName() + ": " + clientChoice);

				// Output message
				if (clientChoice.equalsIgnoreCase("FIN")) {
					System.out.println("Cerramos conexión a petición del cliente.");
					connectionStatus = false;
					ps.println("OK");
					// Enviamos mensaje para cerrar socket en el lado del cliente.
					socketToClient.close();
					socketsArray.remove(this.socketToClient);
					System.out.println("- Clientes conectados: " + socketsArray.size());
				} else {

					switch (Integer.parseInt(clientChoice)) {
					case 0:
						// Create Film
						response = gson.toJson(filmService.createFilm((Film) objectSend));
						break;
					case 1:
						// Get Film By ID
						
						response = gson.toJson(filmService.getFilmById(Integer.parseInt(inputMessage.split("_")[1])));
						break;
					case 2:
						// Get Film By Title
						response = gson.toJson(filmService.getFilmByTitle((String) objectSend));
						break;
					case 3:
						// Update Film
						response = gson.toJson(filmService.updateFilmById((Film) objectSend));
						break;
					case 4:
						// Get Films By Director ID
						response = gson.toJson(filmService.getFilmsByDirectorId(num_cliente));
						break;
					case 5:
						// Delete Film By ID
						response = gson.toJson(filmService.deleteFilmById((Integer) objectSend));
						break;
					case 7:
						// Create Director
						Director d = gson.fromJson(inputMessage.split("_")[1], Director.class);
						response = gson.toJson(directorService.createDirector(d));
						break;
					case 8:
						// Get Director By ID
						response = gson.toJson(directorService.getDirectorById((Integer) objectSend));
						break;
					case 9:
						// Get Director By Name
						response = gson.toJson(directorService.getDirectorByName((String) objectSend));
						break;
					case 10:
						// Update Director By ID
						response = gson.toJson(directorService.updateDirectorById((Director) objectSend));
						break;
					case 11:
						// Delete Director By ID
						response = gson.toJson(directorService.deleteDirectorById((Integer) objectSend));
						break;
					}

					ps.println(response);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package main;

import java.io.IOException;
import java.util.Scanner;

import main.entity.Director;
import main.entity.Film;
import model.service.InputOutputDataService;
import model.service.ServerConnectionService;

public class MainClient {

	public static void main(String[] args) {

		ServerConnectionService c = ServerConnectionService.getInstance();
		InputOutputDataService iou = InputOutputDataService.getInstance();

		Director d = new Director();
		d.setName("Ridley Scott Modificado");
		d.setId(5);

		Film f = new Film();
		f.setDirector(d);
		f.setRating(10);
		f.setId(3);
		f.setTitle("Alien: El Octavo Pasajero");

		String request = iou.requestFormatter(3, f);

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter to start connection");
		sc.nextLine();
		c.startConnection();

		String response = "";

		System.out.println("Enter to send request");
		sc.nextLine();

		try {
			response = c.sendData(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("RESPONSE FROM SERVER: " + response);

		System.out.println("Enter to end connection.");
		sc.nextLine();
		c.endConnection();

	}
}

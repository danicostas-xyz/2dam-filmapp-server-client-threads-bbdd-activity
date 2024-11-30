package model.persistence.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;

import main.entity.Director;
import main.entity.Film;

public class DaoServerConnection {

	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Scanner sc = new Scanner(System.in);
	private Gson gson;

	public static DaoServerConnection dao;

	private DaoServerConnection() {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
		gson = new Gson();
	}

	public static DaoServerConnection getInstance() {
		return (dao == null) ? dao = new DaoServerConnection() : dao;
	}

	public void conexion() {

		boolean connectionStatus = true;

		try (Socket socketToServer = new Socket();) {
			
			socketToServer.connect(address);

			PrintStream ps = new PrintStream(socketToServer.getOutputStream());
			InputStreamReader isr = new InputStreamReader(socketToServer.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			while (connectionStatus) {

				// Output message
				Director d = new Director();
				d.setName("David Lynch");
				System.out.println("Escribe mensaje al server: ");
				String mensaje = sc.nextLine();
				ps.println("1_" + 1);

				System.out.println("Paso antes del readLine()");
				// Input message
				String entrada = br.readLine();
				System.out.println("Paso antes del if");

				if (entrada.equalsIgnoreCase("OK")) {
					System.out.println("Server cierra conexión");
					connectionStatus = false;
					socketToServer.close();
				} else {
					Film f = (Film) gson.fromJson(entrada, Film.class);
					System.out.println(f);
					System.out.println("Paso por la salida");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

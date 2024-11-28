package model.persistence.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class DaoServerConnection {

	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Socket socketToServer;
	private Scanner sc = new Scanner(System.in);

	public static DaoServerConnection dao;

	private DaoServerConnection() {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
	}

	public static DaoServerConnection getInstance() {
		return (dao == null) ? dao = new DaoServerConnection() : dao;
	}

	public void prueba() {

		boolean connectionStatus = true;

		try {
			socketToServer = new Socket();
			socketToServer.connect(address);
			PrintStream ps = new PrintStream(socketToServer.getOutputStream());
			while (connectionStatus) {

				System.out.println("Escribe mensaje al server: ");
				String mensaje = sc.nextLine();
				ps.println(mensaje);

				InputStreamReader isr = new InputStreamReader(socketToServer.getInputStream());
				BufferedReader br = new BufferedReader(isr);

				String entrada = br.readLine();

				if (entrada.equalsIgnoreCase("OK")) {
					System.out.println("Server cierra conexión");
					connectionStatus = false;
					socketToServer.close();
				} else {
					System.out.println(entrada);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

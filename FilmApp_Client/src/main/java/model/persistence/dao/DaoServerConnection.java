package model.persistence.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.google.gson.Gson;

import main.entity.Director;
import main.entity.Film;

public class DaoServerConnection {

	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Gson gson;
	private final String END_CONNECTION_SIGNAL = "OK";

	public static DaoServerConnection dao;

	private DaoServerConnection() {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
		gson = new Gson();
	}

	public static DaoServerConnection getInstance() {
		return (dao == null) ? dao = new DaoServerConnection() : dao;
	}

	public void sendData(String requestOutput) {

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

				if (entrada.equalsIgnoreCase(END_CONNECTION_SIGNAL)) {
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
}

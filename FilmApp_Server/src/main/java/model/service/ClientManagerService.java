package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientManagerService implements Runnable {

	private Socket socketToClient;
	private Thread thread;
	private static int num_cliente;

	public ClientManagerService(Socket socketToClient) {
		this.socketToClient = socketToClient;
		thread = new Thread(this, "Cliente " + num_cliente);
		thread.start();
	}

	@Override
	public void run() {
		boolean connectionStatus = true;

		try (InputStreamReader isr = new InputStreamReader(socketToClient.getInputStream())) {
			BufferedReader br = new BufferedReader(isr);

			while (connectionStatus) {

				String entrada = br.readLine();
				System.out.println("Mensaje del cliente: ");
				PrintStream ps = new PrintStream(socketToClient.getOutputStream());

				if (entrada.equalsIgnoreCase("FIN")) {
					System.out.println("Cliente cierra la conexión");
					connectionStatus = false;
					ps.println("OK");
					socketToClient.close();
				} else {
					System.out.println(entrada);
					ps.println(entrada + "de vuelta");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientManagerService implements Runnable {

	private Socket socketToClient;
	private Thread thread;
	private List<Socket> socketsArray;
	private static int num_cliente;

	public ClientManagerService(Socket socketToClient, List<Socket> socketsArray) {
		this.socketToClient = socketToClient;
		this.socketsArray = socketsArray;
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

				// Input message
				String entrada = br.readLine();
				System.out.println("Mensaje del cliente " + Thread.currentThread().getName() + ": " + entrada);

				// Output message
				if (entrada.equalsIgnoreCase("FIN")) {
					System.out.println("Cerramos conexión a petición del cliente.");
					connectionStatus = false;
					ps.println("OK");
					// Enviamos mensaje para cerrar socket en el lado del cliente.
					socketToClient.close();
					socketsArray.remove(this.socketToClient);
					System.out.println("- Clientes conectados: " + socketsArray.size());
				} else {
					System.out.println(entrada);
					ps.println(entrada + " de vuelta");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

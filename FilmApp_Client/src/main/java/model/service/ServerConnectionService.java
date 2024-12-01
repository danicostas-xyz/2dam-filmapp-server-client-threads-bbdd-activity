package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerConnectionService {

	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Socket socketToServer;
	private PrintStream ps;
	private InputStreamReader isr;
	private BufferedReader br;
	private boolean connectionStatus;
	private final String END_CONNECTION_SIGNAL = "OK";

	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public static ServerConnectionService serverConnection;

	private ServerConnectionService() {

	}

	public static ServerConnectionService getInstance() {
		return (serverConnection == null) ? serverConnection = new ServerConnectionService() : serverConnection;
	}

	public void startConnection() throws IOException {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
		try {
			socketToServer = new Socket();
			socketToServer.connect(address);
			ps = new PrintStream(socketToServer.getOutputStream());
			isr = new InputStreamReader(socketToServer.getInputStream());
			br = new BufferedReader(isr);

		} catch (IOException e) {
//			e.printStackTrace();
			throw e;
		}
		connectionStatus = true;
	}

	/**
	 * Método que recibe una request "X_JSON" y devuelve la respuesta del server en
	 * formato ArraString (JSON o String puro)
	 * 
	 * @param requestOutput "X_JSON"
	 * @return
	 * @throws IOException
	 */
	public String sendData(String requestOutput) throws IOException {

		// Output message
		ps.println(requestOutput);

		// Input message
		String input = br.readLine();

		if (input.equalsIgnoreCase(END_CONNECTION_SIGNAL)) {
			endConnection();
			return "Server closes connection.";
		}

		return input;
	}

	public void endConnection() {

		try {
			socketToServer.close();
			ps.close();
			isr.close();
			br.close();
			connectionStatus = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package model.persistence.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DaoServerConnection {

	private final String IP_ADDRESS = "127.0.0.1";
	private final int PORT = 2024;
	private InetSocketAddress address;
	private Socket socketToServer;

	public static DaoServerConnection dao;

	private DaoServerConnection() {
		address = new InetSocketAddress(IP_ADDRESS, PORT);
	}

	public static DaoServerConnection getInstance() {
		return (dao == null) ? dao = new DaoServerConnection() : dao;
	}

	public void prueba(String m) {
		try {
			socketToServer = new Socket();
			socketToServer.connect(address);
			OutputStream os = socketToServer.getOutputStream();
			PrintStream ps = new PrintStream(os);
			ps.print(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String entrada = "";
		
		try (InputStream input = socketToServer.getInputStream();) {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			while (br != null) {
				entrada = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(entrada);

	}

}

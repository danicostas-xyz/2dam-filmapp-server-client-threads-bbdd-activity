package model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientManagerService implements Runnable{
	
	private Socket socketToClient;
	
	public ClientManagerService(Socket socketToClient) {
		this.socketToClient = socketToClient;
	}
	

	@Override
	public void run() {
		
		String entrada = "";
		try (InputStream input = socketToClient.getInputStream();) {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			while (br != null) {
				entrada = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OutputStream os = socketToClient.getOutputStream();
			PrintStream ps = new PrintStream(os);
			ps.print(entrada + " de vuelta.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

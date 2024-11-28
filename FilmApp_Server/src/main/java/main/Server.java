package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.service.ClientManagerService;

public class Server {
	
	private final static int PORT = 2024;
	public static List<Socket> socketsArray = new ArrayList<>();
	
	
	public static void main(String[] args) {

			try (ServerSocket serverSocket = new ServerSocket();) {
				InetSocketAddress address = new InetSocketAddress(PORT);
				
				serverSocket.bind(address);
				
				while(true) {
					System.out.println("- Clientes conectados: " + socketsArray.size());
					System.out.println("Waiting for a client...");
					Socket socketToClient = serverSocket.accept();
					socketsArray.add(socketToClient);
					new ClientManagerService(socketToClient, socketsArray);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


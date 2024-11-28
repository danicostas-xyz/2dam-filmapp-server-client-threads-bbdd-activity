package main;

import java.util.Scanner;

import model.persistence.dao.DaoServerConnection;

public class MainClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Message to the server: ");
		DaoServerConnection.getInstance().conexion();
	}

}

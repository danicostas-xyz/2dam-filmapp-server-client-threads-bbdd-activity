package main;

import java.util.Scanner;

import model.persistence.dao.DaoServerConnection;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Message to the server: ");
		String m = sc.nextLine();
		DaoServerConnection.getInstance().prueba(m);
	}

}

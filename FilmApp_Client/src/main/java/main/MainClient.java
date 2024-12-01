package main;

import model.persistence.dao.DaoServerConnection;

public class MainClient {

	public static void main(String[] args) {
		DaoServerConnection.getInstance().conexion();
	}
}

package model.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.entity.Director;
import main.entity.Film;
import model.service.InputOutputDataService;
import model.service.ServerConnectionService;

public class UserInterfaceCLI {

	private final int ULTRA_LOW_SPEED = 750;
	private final int VERY_LOW_SPEED = 150;
	private final int LOW_SPEED = 50;
	private final int FAST_SPEED = 25;
	private final int ULTRA_FAST_SPEED = 5;

	private InputOutputDataService inOutDataService;
	private ServerConnectionService serverConnection;
	private boolean keepRuning;
	private Scanner scInt;
	private Scanner scStr;

	public static UserInterfaceCLI userInterface;

	private UserInterfaceCLI() {
		this.inOutDataService = InputOutputDataService.getInstance();
		this.serverConnection = ServerConnectionService.getInstance();
		this.scInt = new Scanner(System.in);
		this.scStr = new Scanner(System.in);
		this.keepRuning = true;
	}

	public static UserInterfaceCLI getInstance() {
		return (userInterface == null) ? userInterface = new UserInterfaceCLI() : userInterface;
	}

	public void runApp() {

		printSlowly("CARGANDO", LOW_SPEED);
		print3Points(ULTRA_LOW_SPEED, 5);
		printSlowly("\nIniciando conexión", LOW_SPEED);
		print3Points(ULTRA_LOW_SPEED, 5);

		try {
			serverConnection.startConnection();
			printlnSlowly("\nConexión establecida", LOW_SPEED);

			System.out.println("\n================================");
			System.out.println("          MENÚ PRINCIPAL     ");
			System.out.println("================================");

			printMainMenu();
			int option = validarOpcion(0, 11);

//			boolean opcionValida = false;

//			while(!opcionValida) {
//				try {
//					opcion = printMenu();
//					while (opcion < 0 || opcion > 6) {
//						System.out.print("Introduce un número entre 0 y 6: ");
//						opcion = scInt.nextInt();
//					}
//					opcionValida = true;
//					
//				} catch (InputMismatchException e) {
//					System.out.println("Introduce un carácter válido");
//					
//				}
//				opcion = scInt.nextInt();
//			}

			while (keepRuning) {

				switch (option) {
				case 0:
					keepRuning = false;
					break;
				case 1:
					createFilm(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 2:
					getFilmByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 3:
					getFilmByTitle(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 4:
					updateFilmByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 5:
					getFilmsByDirectorId(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 6:
					deleteFilmByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 7:
					createDirector(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 8:
					getDirectorByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 9:
					getDirectorByName(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 10:
					updateDirectorByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				case 11:
					deleteDirectorByID(option);
					printMainMenu();
					option = validarOpcion(0, 11);
					break;
				}

			}

			serverConnection.endConnection();

			System.out.println("\n============================");
			System.out.println("       FIN DEL PROGRAMA      ");
			System.out.println("=============================");

		} catch (IOException e) {
			printlnSlowly("\nError en la conexión.", LOW_SPEED);
//			e.printStackTrace();
			printlnSlowly(e.getLocalizedMessage(), LOW_SPEED);
		}

	}

	private void createDirector(int option) {

		Director director = new Director();

		System.out.println("- Nombre: ");
		director.setName(scStr.nextLine());

		String request = inOutDataService.requestFormatter(option, director);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Integer.parseInt(response) == 1) {
			System.out.println("Director creado correctamente");
		} else
			System.out.println("Ha habido un error al crear al director");

	}

	private void getDirectorByName(int option) {
		Director director = new Director();

		System.out.println("- Director Name: ");
		String directorName = scStr.nextLine();
		director.setName(directorName);

		String request = inOutDataService.requestFormatter(option, directorName);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		director = (Director) inOutDataService.responseFormatter(option, response);
		System.out.println(director);

	}

	private void getDirectorByID(int option) {
		Director director = new Director();

		System.out.println("- Director ID: ");
		int directorID = scInt.nextInt();
		director.setId(directorID);

		String request = inOutDataService.requestFormatter(option, directorID);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		director = (Director) inOutDataService.responseFormatter(option, response);
		System.out.println(director);

	}

	@SuppressWarnings("unchecked")
	private void getFilmsByDirectorId(int option) {
		Director director = new Director();

		System.out.println("- Director ID: ");
		int directorID = scInt.nextInt();
		director.setId(directorID);

		String request = inOutDataService.requestFormatter(option, directorID);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Film> filmList = new ArrayList<>();

		filmList = (ArrayList<Film>) inOutDataService.responseFormatter(option, response);
		System.out.println(filmList);

	}

	private void deleteDirectorByID(int option) {
		System.out.println("- Director ID: ");
		int directorID = scInt.nextInt();
		String request = inOutDataService.requestFormatter(option, directorID);
		String response = "";
		
		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Integer.parseInt(response) == 1) {
			System.out.println("Director eliminado correctamente");
		} else
			System.out.println("Ha habido un error al eliminar al director");
	}

	private void deleteFilmByID(int option) {
		System.out.println("- Film ID: ");
		int filmID = scInt.nextInt();
		String request = inOutDataService.requestFormatter(option, filmID);
		String response = "";
		
		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Integer.parseInt(response) == 1) {
			System.out.println("Película eliminada correctamente");
		} else
			System.out.println("Ha habido un error al eliminar la película");

	}

	private void getFilmByTitle(int option) {
		Film film = new Film();

		System.out.println("- Film Name: ");
		String filmName = scStr.nextLine();
		film.setTitle(filmName);

		String request = inOutDataService.requestFormatter(option, filmName);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		film = (Film) inOutDataService.responseFormatter(option, response);
		System.out.println(film);


	}

	private void getFilmByID(int option) {
		Film film = new Film();

		System.out.println("- Film ID: ");
		int filmID = scInt.nextInt();
		film.setId(filmID);

		String request = inOutDataService.requestFormatter(option, filmID);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		film = (Film) inOutDataService.responseFormatter(option, response);
		System.out.println(film);

	}

	private void updateFilmByID(int option) {
		Film film = new Film();
		Director director = new Director();
		System.out.println("- FilmID a modificar: ");
		int filmID = scInt.nextInt();
		film.setId(filmID);
		System.out.println("- Título: ");
		film.setTitle(scStr.nextLine());
		System.out.println("- DirectorID: ");
		int directorID = scInt.nextInt();
		director.setId(directorID);
		film.setDirector(director);
		System.out.println("- Rating: ");
		film.setRating(scInt.nextDouble());

		String request = inOutDataService.requestFormatter(option, film);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Integer.parseInt(response) == 1) {
			System.out.println("Película modificada correctamente");
		} else
			System.out.println("Ha habido un error al modificar la película");

	}

	private void updateDirectorByID(int option) {

		Director director = new Director();
		
		System.out.println("- DirectorID a modificar: ");
		director.setId(scInt.nextInt());
		System.out.println("- Nombre: ");
		director.setName(scStr.nextLine());

		String request = inOutDataService.requestFormatter(option, director);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Integer.parseInt(response) == 1) {
			System.out.println("Director modificado correctamente");
		} else
			System.out.println("Ha habido un error al modificar al director");

	}

	private void createFilm(int option) {

		Film film = new Film();
		Director director = new Director();

		System.out.println("- Título: ");
		film.setTitle(scStr.nextLine());
		System.out.println("- DirectorID: ");
		int directorID = scInt.nextInt();
		director.setId(directorID);
		film.setDirector(director);
		System.out.println("- Rating: ");
		film.setRating(scInt.nextDouble());

		String request = inOutDataService.requestFormatter(option, film);
		String response = "";

		try {
			response = serverConnection.sendData(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Integer.parseInt(response) == 1) {
			System.out.println("Película creada correctamente");
		} else
			System.out.println("Ha habido un error al crear la película");

	}

	private void printMainMenu() {
	    System.out.println("\n================================");
	    printlnSlowly("🚪  0. SALIR", 5);
	    printlnSlowly("🎬  1. CREAR PELÍCULA", 5);
	    printlnSlowly("🔍  2. SELECCIONAR PELÍCULA POR ID", 5);
	    printlnSlowly("🔎  3. SELECCIONAR PELÍCULA POR TÍTULO", 5);
	    printlnSlowly("✏️  4. MODIFICAR PELÍCULA POR ID", 5);
	    printlnSlowly("🎞️  5. OBTENER LISTA DE PELÍCULAS POR ID DE DIRECTOR", 5);
	    printlnSlowly("🗑️  6. ELIMINAR PELÍCULA POR ID", 5);
	    printlnSlowly("🎬  7. CREAR DIRECTOR", 5);
	    printlnSlowly("🧑‍  8. SELECCIONAR DIRECTOR POR ID", 5);
	    printlnSlowly("🔤  9. SELECCIONAR DIRECTOR POR NOMBRE", 5);
	    printlnSlowly("✏️  10. MODIFICAR DIRECTOR POR ID", 5);
	    printlnSlowly("🗑️  11. ELIMINAR DIRECTOR POR ID", 5);
	    System.out.println("================================\n");
	    printSlowly("➡️  Seleccione una opción: ", 5);
	}


	private int validarIntNoVacio(String atributo) {
		int i = scInt.nextInt();

		while (i == 0) {
			printlnSlowly("Campo vacío no válido.", 25);
			printlnSlowly("- Introduce un valor para " + atributo, 25);
			i = scInt.nextInt();
		}
		return i;
	}

	private String validarStringNoVacio(String atributo) {

		String s = scStr.nextLine();

		while (s.isBlank()) {
			printlnSlowly("Campo vacío no válido.", 25);
			printlnSlowly("- Introduce un valor para " + atributo, 25);
			s = scStr.nextLine();
		}
		return s;
	}

	private int validarOpcion(int i, int j) {
		int opcion = scInt.nextInt();
		System.out.println("");
		while (opcion < i || opcion > j) {
			printlnSlowly("  [ERROR] Introduce una opción correcta", 25);
			System.out.println();
			printSlowly("- Introduce un número entre " + i + " y " + j + ": ", 25);
			opcion = scInt.nextInt();
		}
		return opcion;
	}

	public static void print3Points(int time, int time2) {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(time);
				System.out.print(".");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(time2);
			System.out.print(" ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void printSlowly(String s, int time) {
		for (int i = 0; i < s.length(); i++) {
			try {
				Thread.sleep(time);
				System.out.print(s.charAt(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void printlnSlowly(String s, int time) {
		for (int i = 0; i < s.length(); i++) {
			try {
				Thread.sleep(time);
				System.out.print(s.charAt(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
	}

}

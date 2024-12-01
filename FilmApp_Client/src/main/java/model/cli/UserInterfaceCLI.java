//package model.cli;
//
//import java.util.Scanner;
//
//import model.service.InputOutputDataService;
//import model.service.ServerConnectionService;
//
//public class UserInterfaceCLI {
//
//	private InputOutputDataService inOutDataService;
//	private ServerConnectionService serverConnection;
//	private boolean keepRuning;
//	private Scanner scInt;
//	private Scanner scStr;
//	
//	public static UserInterfaceCLI userInterface;
//	private UserInterfaceCLI() {
//		this.inOutDataService = InputOutputDataService.getInstance();
//		this.serverConnection = ServerConnectionService.getInstance();
//		this.keepRuning = true;
//		this.scInt = new Scanner(System.in);
//		this.scStr = new Scanner(System.in);
//	}
//	public static UserInterfaceCLI getInstance() {
//		return (userInterface == null) ? userInterface = new UserInterfaceCLI() : userInterface;
//	}
//	
//	public void runApp() {
//
//		printSlowly("CARGANDO", 5);
//		print3Points(5, 5);
//
//		System.out.println("\n================================");
//		System.out.println("          MENÚ PRINCIPAL     ");
//		System.out.println("================================");
//
//		printMainMenu();
//		int opcion = validarOpcion(0, 7);
//
////		boolean opcionValida = false;
//
////		while(!opcionValida) {
////			try {
////				opcion = printMenu();
////				while (opcion < 0 || opcion > 6) {
////					System.out.print("Introduce un número entre 0 y 6: ");
////					opcion = scInt.nextInt();
////				}
////				opcionValida = true;
////				
////			} catch (InputMismatchException e) {
////				System.out.println("Introduce un carácter válido");
////				
////			}
////			opcion = scInt.nextInt();
////		}
//
//		while (opcion != 0) {
//
//			switch (opcion) {
//			case 1:
//				altaCoche();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 2:
//				bajaCochePorId();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 3:
//				modificarCochePorID();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 4:
//				buscarCochePorId();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 5:
//				buscarCochePorMarca();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 6:
//				listarTodosLosCoches();
//				printMainMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 7:
//				gestionPasajeros();
//				return;
//			default:
//				break;
//			}
//
//		}
//
//		System.out.println("\n============================");
//		System.out.println("       FIN DEL PROGRAMA      ");
//		System.out.println("=============================");
//
//	}
//
//	private void gestionPasajeros() {
//
//		System.out.println("\n================================");
//		System.out.println("       MENÚ DE PASAJEROS     ");
//		System.out.println("================================");
//
//		printPassengersMenu();
//		int opcion = validarOpcion(0, 7);
//
//		while (opcion != 0) {
//
//			switch (opcion) {
//			case 1:
//				createPassenger();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 2:
//				deletePassengerById();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 3:
//				getPassengerById();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 4:
//				listAllPassengers();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 5:
//				addPassengerToCar();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 6:
//				deletePassengerFromCar();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			case 7:
//				listPassengersFromCar();
//				printPassengersMenu();
//				opcion = validarOpcion(0, 7);
//				break;
//			} // end switch-case;
//		} // end while();
//		runApp();
//	}
//
//	private Car listPassengersFromCar() {
//
//		askUserIfShowCars();
//
//		System.out.println();
//		printSlowly("Elige el ID del coche del que quieres listar sus pasajeros: ", 25);
//		int option = scInt.nextInt();
//		// TODO Validar la selección
//		Car car = carService.selectById(option);
//		List<Passenger> passengerList = passengerService.listPassengersFromCar(option);
//
//		printlnSlowly(
//				("Lista de pasajeros del coche " + car.getMarca() + " " + car.getModelo() + " (ID " + car.getId() + ")"), 5);
//
//		for (Passenger p : passengerList) {
//			printlnSlowly(p.toString(), 25);
//		}
//
//		return car; // Lo devuelvo para poder usar el coche en otros métodos que usan este método
//
//	}
//
//	private void deletePassengerFromCar() {
//
//		Car car = listPassengersFromCar();
//		System.out.println();
//
//		int option;
//		do {
//			printSlowly("Selecciona el ID del pasajero que quieres sacar del coche: ", 25);
//			int passengerIDSelected = scInt.nextInt();
//			Passenger passenger = passengerService.getPassengerById(passengerIDSelected);
//			printSlowly("Has seleccionado al pasajero " + passenger.getNombre() + "(ID " + passenger.getId() + ")", 5);
//			printlnSlowly("Estás seguro de querer eliminarlo?", 25);
//			printSlowly("Pulsa 1 para eliminarlo o 0 para elegir otra opción: ", 25);
//			option = scInt.nextInt();
//			Integer result = null;
//
//			if (option == 1) {
//				result = passengerService.deletePassengerFromCar(passengerIDSelected);
//				if (result == 0) {
//					printlnSlowly("Pasajero " + passenger.getNombre() + "(ID " + passenger.getId()
//							+ ") eliminado del coche " + car.getMarca() + car.getModelo() + " (ID " + car.getId() + ")",
//							25);
//				} else if (result == null) {
//					printlnSlowly("Ha ocurrido un error inesperado, vuelva a intentarlo más tarde", 25);
//				}
//			}
//		} while (option != 1);
//
//	}
//
//	private void addPassengerToCar() {
//		askUserIfShowPassengers();
//		askUserIfShowCars();
//		Passenger passenger = null;
//		Car car = null;
//		int option;
//		Integer result = null;
//
//		do {
//			printSlowly("Seleccione el ID del coche al que quieras añadir un pasajero: ", 5);
//			int carIDSelected = scInt.nextInt();
//			car = carService.selectById(carIDSelected);
//			printSlowly("Seleccione el ID del pasajero a añadir al coche" + car.getMarca() + " " + car.getModelo()
//					+ "(ID " + car.getId() + "): ", 25);
//			int passengerIDSelected = scInt.nextInt();
//			passenger = passengerService.getPassengerById(passengerIDSelected);
//			printlnSlowly("Desea confirmar los cambios?", 25);
//			printSlowly("Pulsa 1 para confirmar o 0 para volver atrás: ", 25);
//			option = scInt.nextInt();
//
//			if (option == 1) {
//				result = passengerService.addPassengerToCar(passengerIDSelected, carIDSelected);
//				if (result == 0) {
//					printlnSlowly("Pasajero añadido correctamente", 25);
//				} else {
//					printlnSlowly("Ha ocurrido un error inesperado, inténtelo de nuevo más tarde", 25);
//				}
//			}
//
//		} while (option != 1);
//
//	}
//
//	private void listAllPassengers() {
//
//		System.out.println("\n===========================");
//		printlnSlowly("Listar todos los pasajeros ", 5);
//		System.out.println("===========================\n");
//
//		List<Passenger> passengerList = passengerService.listAllPassengers();
//		if (passengerList.size() == 0) {
//			printlnSlowly("No hay ningún pasajero en la Base de Datos", 25);
//		} else {
//			printlnSlowly("Pulsa enter para listar todos los pasajeros ", 25);
//			scStr.nextLine();
//			System.out.println("");
//			for (Passenger p : passengerList) {
//				printlnSlowly(p.toString(), 25);
//			}
//		}
//	}
//
//	private void getPassengerById() {
//		printSlowly("Selecciona el ID del pasajero a consultar: ", 25);
//		int passengerIDSelected = scInt.nextInt();
//		Passenger passenger = passengerService.getPassengerById(passengerIDSelected);
//		if (passenger != null) {
//			// TODO falta controlar el return de getPassengerById, porque null es si hay I/O
//			// Exception, pero debería devolver 1 si no hay ningún pasajero con dicho ID
//			printlnSlowly("Pasajero seleccionado: ", 25);
//			printSlowly(passenger.toString(), 25);
//		}
//
//	}
//
//	private void deletePassengerById() {
//		askUserIfShowPassengers();
//		int option;
//		do {
//			printSlowly("Selecciona el ID del pasajero a borrar: ", 25);
//			int passengerIDSelected = scInt.nextInt();
//			Passenger passenger = passengerService.getPassengerById(passengerIDSelected);
//			if (passenger != null) {
//				// TODO falta controlar el return de getPassengerById, porque null es si hay I/O
//				// Exception, pero debería devolver 1 si no hay ningún pasajero con dicho ID
//				printlnSlowly("Pasajero seleccionado: ", 25);
//				printSlowly(passenger.toString(), 25);
//			}
//
//			printlnSlowly("Estás seguro de querer eliminarlo?", 25);
//			printSlowly("Pulsa 1 para eliminarlo o 0 para elegir otra opción: ", 25);
//			option = scInt.nextInt();
//			Integer result = null;
//
//			if (option == 1) {
//				result = passengerService.deletePassengerById(passengerIDSelected);
//				if (result == 0) {
//					printlnSlowly("Pasajero " + passenger.getNombre() + "(ID " + passenger.getId()
//							+ ") eliminado correctamente.", 25);
//				} else if (result == null) {
//					printlnSlowly("Ha ocurrido un error inesperado, vuelva a intentarlo más tarde", 25);
//				}
//			}
//		} while (option != 1);
//
//	}
//
//	private void createPassenger() {
//		Passenger p = new Passenger();
//		int option;
//		Integer result = null;
//
//		do {
//			printlnSlowly("Cumplimenta los datos del pasajero a añadir: ", 25);
//			printSlowly("- Nombre: ", 25);
//			p.setNombre(scStr.nextLine());
//			printSlowly("- Edad: ", 25);
//			p.setEdad(scInt.nextInt());
//			printSlowly("- Peso: ", 25);
//			p.setPeso(scInt.nextDouble());
//
//			printlnSlowly("Quieres guardar los datos?", 25);
//			printSlowly("Pulsa 1 para guardarlo o 0 para modificar los datos: ", 25);
//			option = scInt.nextInt();
//			if (option == 1) {
//				result = passengerService.createPassenger(p);
//				if (result == 0) {
//					printlnSlowly("Pasajero creado correctamente", 25);
//				} else {
//					printlnSlowly("Ha ocurrido un error inesperado, inténtelo de nuevo más tarde.", 25);
//				}
//			}
//		} while (option != 1);
//
//	}
//
//	private void askUserIfShowPassengers() {
//		printlnSlowly("¿Quieres mostrar todos los pasajeros primero?", 25);
//		printlnSlowly("Pulsa 1 para mostrar los pasajeros o 0 para introducir el ID del pasajero", 25);
//		int option = scInt.nextInt();
//
//		if (option == 1) {
//			listAllPassengers();
//		}
//	}
//
//	private void askUserIfShowCars() {
//		printlnSlowly("¿Quieres mostrar todos los coches primero?", 25);
//		printlnSlowly("Pulsa 1 para mostrar los coches o 0 para introducir el ID del coche", 25);
//		int option = scInt.nextInt();
//
//		if (option == 1) {
//			listarTodosLosCoches();
//		}
//	}
//
//	private void printMainMenu() {
//		System.out.println("\n================================");
//		printlnSlowly("- 0. Salir de la aplicación", 5);
//		printlnSlowly("- 1. Dar de alta un coche", 5);
//		printlnSlowly("- 2. Dar de baja un coche por ID", 5);
//		printlnSlowly("- 3. Modificar un coche por ID", 5);
//		printlnSlowly("- 4. Buscar un coche por ID", 5);
//		printlnSlowly("- 5. Buscar coches por marca", 5);
//		printlnSlowly("- 6. Listar todos los coches", 5);
//		printlnSlowly("- 7. Gestión de Pasajeros", 5);
//
//		System.out.println("================================\n");
//		printSlowly("- Seleccione una opción: ", 5);
//	}
//
//	private void printPassengersMenu() {
//		System.out.println("\n================================");
//		printlnSlowly("- 0. Volver al menú principal", 5);
//		printlnSlowly("- 1. Crear nuevo pasajero", 5);
//		printlnSlowly("- 2. Borrar pasajero por ID", 5);
//		printlnSlowly("- 3. Consulta pasajero por ID", 5);
//		printlnSlowly("- 4. Listar todos los pasajeros", 5);
//		printlnSlowly("- 5. Añadir pasajero a un coche", 5);
//		printlnSlowly("- 6. Eliminar el pasajero de un coche", 5);
//		printlnSlowly("- 7. Listar todos los pasajeros de un coche", 5);
//
//		System.out.println("================================\n");
//		printSlowly("- Seleccione una opción: ", 5);
//	}
//
//	private void listarTodosLosCoches() {
//		System.out.println("\n===========================");
//		printlnSlowly("  Listar todos los coches  ", 5);
//		System.out.println("===========================\n");
//
//		List<Car> listaCoches = carService.selectAll();
//		if (listaCoches.size() == 0) {
//			System.out.println("No hay ningún coche en la Base de Datos");
//		} else {
//			System.out.print("Pulsa enter para listar todos los coches ");
//			scStr.nextLine();
//			System.out.println("");
//			for (Car coche : listaCoches) {
//				System.out.println(coche);
//			}
//		}
//
//	}
//
//	private void buscarCochePorId() {
//		System.out.println("\n===========================");
//		printlnSlowly("    Buscar coche por ID    ", 5);
//		System.out.println("===========================\n");
//
//		System.out.println("Seleccione el ID a buscar: ");
//		int id = validarIntNoVacio("ID");
//		Car c = carService.selectById(id);
//		if (c == null) {
//			System.out.println("No se ha encontrado ningún coche con el ID especificado.");
//		} else {
//			System.out.println("Coche seleccionado: ");
//			System.out.println(c);
//		}
//
//	}
//
//	private void buscarCochePorMarca() {
//		System.out.println("\n===========================");
//		printlnSlowly("   Buscar coche por marca  ", 5);
//		System.out.println("===========================\n");
//
//		printSlowly("Seleccione la marca a buscar: ", 25);
//		String marca = validarStringNoVacio("Marca");
//		List<Car> listaCoches = carService.selectByMarca(marca);
//		if (listaCoches.size() == 0) {
//			printlnSlowly("No se ha encontrado ningún coche con la marca especificada.", 25);
//		} else {
//			printlnSlowly("Coches con la marca " + marca + ": ", 25);
//			for (Car coche : listaCoches) {
//				System.out.println(coche);
//			}
//		}
//
//	}
//
//	private void modificarCochePorID() {
//		System.out.println("\n===========================");
//		printlnSlowly("   Modificar coche por ID  ", 5);
//		System.out.println("===========================\n");
//
//		printlnSlowly("Seleccione el ID a modificar: ", 25);
//		int id = validarIntNoVacio("ID");
//		Car c = carService.selectById(id);
//
//		if (c == null) {
//			printlnSlowly("No se ha encontrado ningún coche con el ID especificado.", 25);
//		} else {
//			printlnSlowly("Coche seleccionado: ", 25);
//			System.out.println(c.toString());
//			printlnSlowly("¿Seguro que quieres modificarlo?", 25);
//			printlnSlowly("\n1. Modificarlo", 10);
//			printlnSlowly("2. No modificarlo\n", 10);
//			printSlowly("- Seleccione una opción: ", 25);
//			int opcion = validarOpcion(1, 2);
//			if (opcion == 1) {
//
//				c = pedirDatosCoche();
//				c.setId(id);
//				int resultado = carService.updateById(c);
//
//				if (resultado == 1) {
//					printlnSlowly("Coche con id " + id + " modificado correctamente.", 25);
//				} else {
//					printlnSlowly("Ha ocurrido un error. Inténtelo de nuevo más tarde.", 25);
//				}
//			}
//		}
//
//	}
//
//	private void bajaCochePorId() {
//		System.out.println("\n===========================");
//		printlnSlowly("      Baja coche por ID    ", 5);
//		System.out.println("===========================\n");
//
//		printSlowly("Seleccione el ID a borrar: ", 25);
//		int id = validarIntNoVacio("ID");
//		Car c = carService.selectById(id);
//
//		if (c == null) {
//			printSlowly("No se ha encontrado ningún coche con el ID especificado.", 25);
//		} else {
//			printlnSlowly("Coche seleccionado: ", 25);
//			System.out.println(c.toString());
//			printlnSlowly("¿Seguro que quieres eliminarlo?", 25);
//			printlnSlowly("\n- 1. Eliminarlo", 10);
//			printlnSlowly("- 2. No eliminarlo\n", 10);
//			printSlowly("- Seleccione una opción: ", 25);
//			int opcion = validarOpcion(1, 2);
//			if (opcion == 1) {
//				int resultado = carService.deleteById(id);
//				if (resultado == 1) {
//					printlnSlowly("Coche con ID " + id + " eliminado correctamente.", 25);
//				} else {
//					printlnSlowly("Ha ocurrido un error. Inténtelo de nuevo más tarde.", 25);
//				}
//			}
//		}
//
//	}
//
//	private void altaCoche() {
//		System.out.println("\n===========================");
//		printlnSlowly("    Dar de alta un coche   ", 5);
//		System.out.println("===========================\n");
//		Car c = pedirDatosCoche();
//		int resultado = carService.insert(c);
//		if (resultado == 1) {
//			printlnSlowly("\nCoche insertado correctamente.", 25);
//		} else {
//			printlnSlowly("Ha ocurrido un error. Inténtelo de nuevo más tarde.", 25);
//		}
//	}
//
//	private Car pedirDatosCoche() {
//
//		printSlowly("- Marca: ", 25);
//		String marca = validarStringNoVacio("Marca");
//		printSlowly("- Modelo: ", 25);
//		String modelo = validarStringNoVacio("Modelo");
//		printSlowly("- Kilómetros: ", 25);
//		int kilometros = validarIntNoVacio("Kilometros");
//		printSlowly("- Seleccione un motor: ", 25);
//		MotorType motor = seleccionMotor();
//
//		return new Car(marca, modelo, motor, kilometros);
//
//	}
//
//
//	private int validarIntNoVacio(String atributo) {
//		int i = scInt.nextInt();
//
//		while (i == 0) {
//			printlnSlowly("Campo vacío no válido.", 25);
//			printlnSlowly("- Introduce un valor para " + atributo, 25);
//			i = scInt.nextInt();
//		}
//		return i;
//	}
//
//	private String validarStringNoVacio(String atributo) {
//
//		String s = scStr.nextLine();
//
//		while (s.isBlank()) {
//			printlnSlowly("Campo vacío no válido.", 25);
//			printlnSlowly("- Introduce un valor para " + atributo, 25);
//			s = scStr.nextLine();
//		}
//		return s;
//	}
//
//	private int validarOpcion(int i, int j) {
//		int opcion = scInt.nextInt();
//		System.out.println("");
//		while (opcion < i || opcion > j) {
//			printlnSlowly("  [ERROR] Introduce una opción correcta", 25);
//			System.out.println();
//			printSlowly("- Introduce un número entre " + i + " y " + j + ": ", 25);
//			opcion = scInt.nextInt();
//		}
//		return opcion;
//	}
//
//	public static void print3Points(int time, int time2) {
//		for (int i = 0; i < 3; i++) {
//			try {
//				Thread.sleep(time);
//				System.out.print(".");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			Thread.sleep(time2);
//			System.out.print(" ");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void printSlowly(String s, int time) {
//		for (int i = 0; i < s.length(); i++) {
//			try {
//				Thread.sleep(time);
//				System.out.print(s.charAt(i));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void printlnSlowly(String s, int time) {
//		for (int i = 0; i < s.length(); i++) {
//			try {
//				Thread.sleep(time);
//				System.out.print(s.charAt(i));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("");
//	}
//	
//	
//	
//	
//}

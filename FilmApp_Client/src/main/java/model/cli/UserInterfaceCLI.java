package model.cli;

import model.service.InputOutputDataService;
import model.service.ServerConnectionService;

public class UserInterfaceCLI {

	private InputOutputDataService inOutDataService;
	private ServerConnectionService serverConnection;
	
	public static UserInterfaceCLI userInterface;
	private UserInterfaceCLI() {
		this.inOutDataService = InputOutputDataService.getInstance();
		this.serverConnection = ServerConnectionService.getInstance();
	}
	public static UserInterfaceCLI getInstance() {
		return (userInterface == null) ? userInterface = new UserInterfaceCLI() : userInterface;
	}
	
}

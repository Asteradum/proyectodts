package server;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

	private static ServerController serverController= null;
	private static int maxNumberConnections = -1;
	private static int numberClients = 0;
	private List<Server> userList = new ArrayList<Server>();
	private static DaemonServer daemonServer = null;
	
	public int getNumberClients() {
		return numberClients;
	}

	public List<Server> getUserList() {
		return userList;
	}

	public void setMaxNumberConnections(int maxNumberConnections) {
		this.maxNumberConnections = maxNumberConnections;
	}
	
	public int getMaxNumberConnections() {
		return maxNumberConnections;
	}

	public void removeServer(Server server){
		userList.remove(server);
		
	}
	
	public static void main(String[] args) {
		serverController = new ServerController();
		daemonServer = new DaemonServer(serverController);
		daemonServer.start(args[0]);

	}

}

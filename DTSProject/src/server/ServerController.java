package server;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

	private static ServerController serverController= null;
	private static int maxNumberConnections = -1;
	private static int numberClients = 0;
	private List<Server> userList = new ArrayList<Server>();
	private static DaemonServer daemonServer = null;
	private ServerGUI form = null;

	
	public int getNumberClients() {
		return userList.size();
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
	public void removeServerThread(long threadID){
		boolean finded = false;
		int i=0;
		while ((!finded)/* || (i<userList.size())*/) 
			if (userList.get(i).getId() == threadID)
				finded = true;
			else i++;
		userList.remove(i);
		numberClients = userList.size();		
	}
	public ServerController()  {
		form = new ServerGUI(this);
	}
	public static void main(String[] args) {
		serverController = new ServerController();
		daemonServer = new DaemonServer(serverController);
		daemonServer.start(args[0]);
		new ServerController();

	}

}

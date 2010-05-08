package server;

import java.util.ArrayList;
import java.util.List;

public class ServerController {

	private static ServerController serverController= null;
	private static int maxNumberConnections = -1;
	private static int numberClients = 0;
	private static List<Server> userList = new ArrayList<Server>();
	private static DaemonServer daemonServer = null;
	private static ServerGUI form = null;
	private List<Long> lastT= new ArrayList<Long>() ;
	List<String> list=new ArrayList<String>();
	

	
	public int getNumberClients() {
		return numberClients;
		
	}

	public List<Server> getUserList() {
		return userList;
	}

	public void alert(String name, long l)
	{ System.out.println(name + l);
	
	if (lastT.size()==0)
	{list.add(name+";"+l);
	lastT.add(l);
	form.Change(list);}
	else {
		boolean newT=false;
			for (int i=0;i< lastT.size();i++)
			{if (lastT.get(i)==l)
				{newT=true;
				break;
				}
				
				}
		 if (newT==false)
				{list.add(name+";"+l);
				lastT.add(l);
				form.Change(list);}}
	}
		
	
		/*for (int i=0;i<userList.size();i++)
			if (getUserList().get(num).getUserID()!="null")
					state=1;
		if (state==1)
		form.Change();*/	
		
	
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
		
		

	}

}

package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerController {

	private static ServerController serverController= null;
	private  int maxNumberConnections = -1;
	private static int numberClients = 0;
	private static List<Server> userList = new ArrayList<Server>();
	private static DaemonServer daemonServer = null;
	private static ServerGUI form = null;
	private List<Long> lastT= new ArrayList<Long>() ;
	//List<String> list=new ArrayList<String>();
	

	
	public int getNumberClients() {
		return userList.size();
		
	}

	public List<Server> getUserList() {
		return userList;
	}

	/*public void alert(String name, long l)
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
	}*/
		
	public void alertN (Long t)
	{ if (lastT.size()==0)
	{lastT.add(t);
	form.Change(lastT);}
	else {
		boolean newT=false;
			for (int i=0;i< lastT.size();i++)
			{if (lastT.get(i)==t)
				{newT=true;
				break;
				}
				
				}
		 if (newT==false)
				{lastT.add(t);
				form.Change(lastT);}}
		 
	}
	public void notifyServerGUI ()
	{
		form.getStatusBar().setText("A new clien has tryied to connect with this server");
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
		try {
			userList.get(i).closeClient();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userList.remove(i);
		
		
		for (int j=0;j<lastT.size();j++)
		{if (threadID==lastT.get(j))
			lastT.remove(j);
		}
		form.Change(lastT);
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

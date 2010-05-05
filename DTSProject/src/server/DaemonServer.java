/*
 * @(#)DiccionarioDemonio.html
 * Proyecto Diccionario remoto
 */


package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import data.*;

/**
 * The DaemonServer class contains the implementation of the daemon (process)
 * that waits for connection requests.
 *
 */
public class DaemonServer {

	private static int maxNumberConnections = -1;
	private static int numberClients = 0;
	private static List<Server> userList = new ArrayList<Server>();
	
	public int getNumberClients() {
		return numberClients;
	}

	public void setMaxNumberConnections(int maxNumberConnections) {
		this.maxNumberConnections = maxNumberConnections;
	}

	public int getNumberConnections() {
		return maxNumberConnections;
	}
	
	public void closeUserConnection(long threadID){
		boolean finded = false;
		
		int i = 0;
		while (!finded && i<userList.size()){
			if (userList.get(i).getId() == threadID) 
				finded = true;
			else i++;
		}
		try {
			userList.get(i).closeServer();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public static void main(String args[]){
	    try{
	      ServerSocket serverSocket = new ServerSocket(DataConnection.PORT);
	      serverSocket.setSoTimeout(3000);
	      for(;;){
	    	  if ((maxNumberConnections < 0) || (numberClients < maxNumberConnections)){
	    		  try{
				        Server server = new Server(args[0], serverSocket.accept());
				        userList.add(server);
				        server.start();
				        System.out.println("Executing server process");
				        numberClients++;
	    		  } catch (SocketTimeoutException  e) {
	    				System.out.println("Still waiting...");
	    				System.out.println(numberClients);
	    				for(int i = 0; i<userList.size();i++)
	    					System.out.println(userList.get(i).getId() + "   " + userList.get(i).getUserID() + "   " + userList.get(i).getPass() );
	    		  }
	    		 }
	      }
	    }catch(IOException ioe){
	      System.err.println(ioe);
	    }
	}
}
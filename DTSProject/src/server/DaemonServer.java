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

	private ServerController controller = null;
	

	public DaemonServer(ServerController controller){
		this.controller = controller;
	}

	
	public void start(String VehicleName){
	    try{
	      ServerSocket serverSocket = new ServerSocket(DataConnection.PORT);
	      serverSocket.setSoTimeout(3000);
	      for(;;){
	    	  if ((controller.getMaxNumberConnections() < 0) || (controller.getNumberClients() < controller.getMaxNumberConnections())){
	    		  try{
				      System.out.println(controller.getNumberClients());  
	    			  Server server = new Server(VehicleName, serverSocket.accept(), controller);
				        controller.getUserList().add(server);
				        server.start();
				        for(int i = 0; i<controller.getUserList().size();i++)
    					{System.out.println("lalalal"+controller.getUserList().size());
	    					controller.alertN(controller.getUserList().get(i).getId());}
				        System.out.println("Executing server process");
				        	} catch (SocketTimeoutException  e) {
	    				System.out.println("Still waiting...");
	    				System.out.println(controller.getNumberClients());
	    				Thread t= null;
	    				
    					  
    		  }
	    			/*	for(int i = 0; i<controller.getUserList().size();i++){
	    					t = controller.getUserList().get(i);
	    					if (!t.isAlive())
	    						controller.removeServerThread(t.getId());
	    				}*/		
	    				for(int i = 0; i<controller.getUserList().size();i++)
	    					{System.out.println(controller.getUserList().get(i).getId() + "   " + controller.getUserList().get(i).getUserID() + "   " + controller.getUserList().get(i).getPass() );
	    					  
	    		  }
	    		 }
	    	//  else controller.notifyServerGUI();
	      }
	    }catch(IOException ioe){
	      System.err.println(ioe);
	    }
	}
}
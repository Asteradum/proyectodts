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
				        Server server = new Server(VehicleName, serverSocket.accept(), controller);
				        controller.getUserList().add(server);
				        server.start();
				        System.out.println("Executing server process");
				        controller.setMaxNumberConnections(controller.getNumberClients() + 1);
	    		  } catch (SocketTimeoutException  e) {
	    				System.out.println("Still waiting...");
	    				System.out.println(controller.getNumberClients());
	    				for(int i = 0; i<controller.getUserList().size();i++)
	    					System.out.println(controller.getUserList().get(i).getId() + "   " + controller.getUserList().get(i).getUserID() + "   " + controller.getUserList().get(i).getPass() );
	    		  }
	    		 }
	      }
	    }catch(IOException ioe){
	      System.err.println(ioe);
	    }
	}
}
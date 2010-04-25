/*
 * @(#)DiccionarioDemonio.html
 * Proyecto Diccionario remoto
 */


package server;

import java.net.*;
import java.io.*;
import java.util.*;

import VehicleDAO.Vehicle;
import data.*;

/**
 * The DaemonServer class contains the implementation of the daemon (process)
 * that waits for connection requests.
 *
 */
public class DaemonServer {

  /**
   * 
  * @param args Line-command arguments.
  */
  public static void main(String args[]){
    Vehicle vehicleTable = new Vehicle(null, null, null);
    try{
      ServerSocket serverSocket = new ServerSocket(DataConnection.PORT);
      for(;;){
        Server server = new Server(vehicleTable,serverSocket.accept());
        server.start();
      }
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }
}
/*
 * @(#)DiccionarioDemonio.html
 * Proyecto Diccionario remoto
 */


package server;

import java.net.*;
import java.io.*;

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

    try{
      ServerSocket serverSocket = new ServerSocket(DataConnection.PORT);
      for(;;){
        Server server = new Server(args[0], serverSocket.accept());
        server.start();
      }
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }
}
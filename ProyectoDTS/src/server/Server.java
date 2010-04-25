
package server;

import java.net.*;
import java.io.*;
import java.util.*;

import VehicleDAO.Vehicle;

/**
 * 
 * The Server class has the implementation of the server process
 * that executes in an independent thread. The implementation is
 * in the run() method.
 *
 */
public class Server extends Thread{

  /** Socket which makes the communication possible. */
  private Socket socket = null;

  /** Filter that recieves the data from the socket. */
  private BufferedReader dataReader = null;

  /** Filter that writtes the dato to the socket.. */
  private DataOutputStream dataWritter = null;

  /** Reference to the Vehicle Data. */
  //Cambiar por una clase Vehicle
  private Vehicle  vehicleData = null;

  /**
  * Server constructor, which serves to the client.
  * @param d Tabla de correspondencias de diciconario a utilizar.
  * @param sc Socket que comunica con el cliente conectado.
  */
  public Server( Vehicle d, Socket sc) {
    try{
      vehicleData = d;
      socket = sc;
      dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      dataWritter = new DataOutputStream(socket.getOutputStream());
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }

  /**
  * This process sends information to the client. It has the protocol definition
  */
  public void run(){
    try{
    	
      String line;
      String command;
      String userID="";
      String pass="";
      
      int state = 0;
      
      for (;state<3;){
        line = dataReader.readLine();
        if (line==null) return;
        System.out.println("Recieved line: " + line + " from " + socket.getInetAddress().getHostAddress());
        StringTokenizer sTok= new StringTokenizer(line," ");
        command = sTok.nextToken().toUpperCase();

        switch(state){
          case 0:
        	if(command.equals("USER")){
        		try{
		              userID = sTok.nextToken();
		              dataWritter.writeBytes("201 OK Welcome" + userID + "\r\n");
		              state = 1;
        		}
	        	catch(NoSuchElementException e){
	        		dataWritter.writeBytes("401 ERR Missing username parameter\r\n");
	        	}
            }else if(command.equals("QUIT")){
              state = 3;
            }else{
            	dataWritter.writeBytes("500 ERR Incorrect command\r\n");
            }
	        	
            break;

          case 1:
        	
            if(command.equals("PASS")){
            	try{
		              pass = sTok.nextToken();
		              //Llamar a la clase DAO que se encarga de comprobar los usuarios
		              if(userID.equals("std") && pass.equals("practica")){
		                dataWritter.writeBytes("202 OK Welcome to the system\r\n");
		                state = 2;
		              }else{
		                dataWritter.writeBytes("402 ERR Authentication error\r\n");
		                state = 0;
		              }
            	}
		    	catch(NoSuchElementException e){
		    		dataWritter.writeBytes("403 ERR Missing password parameter\r\n");
		    	}
	            break;
            }else if(command.equals("QUIT")){
              state = 3;
            }else{
              dataWritter.writeBytes("500 ERR Incorrect command\r\n");
            }
		        

          case 2:
            if(command.equals("LISTSENSOR")){
            	dataWritter.writeBytes("112 OK Start of sensor list\r\n");
            	
            	//Introducir los datos de obtencion de sensores
              
              dataWritter.writeBytes("212 OK End of sensor list\r\n");
              
            }else if(command.equals("HISTORYLOG")){
              
            }else if(command.equals("BORRAR")){
              
            }else if(command.equals("SALIR")){
              state = 3;
            }else{
              dataWritter.writeBytes("500 ERR Comando incorrecto\r\n");
            }
            break;
        }
      }
    dataWritter.writeBytes("204 OK Fin de la sesion\r\n");
    dataWritter.close();
    dataReader.close();
    socket.close();
    }catch(IOException ioe){
      System.err.println(ioe);
    }catch(NoSuchElementException nsee){
      System.err.println(nsee);
    }
  }
}
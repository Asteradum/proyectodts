
package server;

import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import server.domainClasses.Vehicle;
import server.userDAO.UserDAO;
import server.vehicleDAO.VehicleDAO;


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

  /** Filter that writes the data to the socket.. */
  private DataOutputStream dataWriter = null;

  /** Reference to the Vehicle Data. */
  private Vehicle vehicleData = null;
  
  private VehicleDAO vehicleDAO = new VehicleDAO();
  
  private UserDAO userDAO = new UserDAO();

  /**
  * Server constructor, which serves to the client.
  * @param d Tabla de correspondencias de diciconario a utilizar.
  * @param sc Socket que comunica con el cliente conectado.
  */
  public Server( String vehicleCode, Socket sc ) {
    try{
      vehicleDAO.connect();
      vehicleData = vehicleDAO.getVehicleInformation(vehicleCode);
      vehicleDAO.disconnect();
      socket = sc;
      dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      dataWriter = new DataOutputStream(socket.getOutputStream());
    }catch(IOException ioe){
      System.err.println(ioe);
    } catch (SQLException e) {
		e.printStackTrace();
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
      
      for (;state<4;){
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
		              dataWriter.writeBytes("201 OK Welcome" + userID + "\r\n");
		              state = 1;
        		}
	        	catch(NoSuchElementException e){
	        		dataWriter.writeBytes("401 ERR Missing username parameter\r\n");
	        	}
            }else if(command.equals("QUIT")){
              state = 4;
            }else{
            	dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
	        	
            break;

          case 1:
        	
            if(command.equals("PASS")){
            	try{
		              pass = sTok.nextToken();
		              userDAO.connect();
		              
		              if(userID.equals(userID) && pass.equals(userDAO.getUserPassword(userID))){
		            	  
		                dataWriter.writeBytes("202 OK Welcome to the system\r\n");
		                state = 2;
		                
		              }else{
		                dataWriter.writeBytes("402 ERR Authentication error\r\n");
		                state = 0;
		              }
		              
		              userDAO.disconnect();
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("403 ERR Missing password parameter\r\n");
		    		state = 0;
		    	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }else if(command.equals("QUIT")){
              state = 4;
            }else{
              dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
		    break;    

          case 2:
            if(command.equals("LISTSENSOR")){
            	dataWriter.writeBytes("112 OK Start of sensor list\r\n");
            	
            	//Introducir los datos de obtencion de sensores
              
              dataWriter.writeBytes("212 OK End of sensor list\r\n");
              
            }else if(command.equals("HISTORYLOG")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*Existe el sensor?*/){
		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n");
		            	  
		            	  //Añadir el log historico del sensor
		            	  
		            	  dataWriter.writeBytes("212 OK End of measurement list\r\n");	  
		              }
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }  		             
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    	}
	              
            }else if(command.equals("ON")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   //Activarlo
		            		   dataWriter.writeBytes("203 OK Sensor activated\r\n");
		            	   }
		            	   else dataWriter.writeBytes("418 ERR Sensor already activated\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }		                       	  
		             
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    	}
            }else if(command.equals("OFF")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   //Desactivarlo
		            		   dataWriter.writeBytes("204 OK Sensor deactivated\r\n");
		            	   }
		            	   else dataWriter.writeBytes("419 ERR Sensor already deactivated\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }		                       	  
		             
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    	}
            }else if(command.equals("GPSON")){
            	
            	if (true/*Esta Activado?*/){
         		   //Activarlo
         		   dataWriter.writeBytes("205 OK GPS activated\r\n");
            	}
         	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
            	
            }else if(command.equals("GPSOFF")){
            	
            	if (true/*Esta Activado?*/){
         		   //Desactivarlo
         		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
            	}
         	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
            	
            }else if(command.equals("GET_CURVALUE")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   
		            		   dataWriter.writeBytes("114 OK ");
		            		   dataWriter.writeBytes(null/*Devolver los datos actuales del sensor en una linea (crear un metodo en la clase)*/);
		            		   dataWriter.writeBytes("\r\n");
		            	   }
		            	   else dataWriter.writeBytes("416 ERR Sensor is not active\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("414 ERR Unknown sensor\r\n");
		              }		                       	  
		             
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    	}
          }else if(command.equals("GET_PIC")){
          	
          	if (true/*Esta Activado el GPS?*/){
          		dataWriter.writeBytes("207 OK Transmitting ");
          		dataWriter.writeBytes(null/*Mostrar bytes de la foto*/ + " bytes");
          		//Enviar foto
          		dataWriter.writeBytes(null/*Mostrar bytes de la foto*/ + " bytes transmitted");
          	}
       	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
          	
          }else if(command.equals("QUIT")){
              state = 4;
            }else{
              dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
         break;
            
         case 3:
        	 
        	 //All the commands accepted in the state 3 are accepted in this state too.
        	 //Just the command GET_LOC has being added.
        	 //When any command is requested, the proccess will return to the state 2.
        	 
        	 if(command.equals("GET_LOC")){
             	
             	if (true/*Esta Activado el GPS?*/){
             		dataWriter.writeBytes("114 OK ");
             		dataWriter.writeBytes(null/*Mostrar posicion actual del vehiculo*/ + " bytes");
             	}
          	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
             	state = 2;
             	
        	 }else if(command.equals("LISTSENSOR")){
            	dataWriter.writeBytes("112 OK Start of sensor list\r\n");
            	
            	//Introducir los datos de obtencion de sensores
              
              dataWriter.writeBytes("212 OK End of sensor list\r\n");
              state = 2;
              
             }else if(command.equals("HISTORYLOG")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*Existe el historico?*/){
		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n");
		            	  
		            	  //Añadir el log historico del sensor
		            	  
		            	  dataWriter.writeBytes("212 OK End of measurement list\r\n");	  
		              }
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }
		              state = 2;
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    		state = 2;
		    	}
	              
            }else if(command.equals("ON")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   //Activarlo
		            		   dataWriter.writeBytes("203 OK Sensor activated\r\n");
		            	   }
		            	   else dataWriter.writeBytes("418 ERR Sensor already activated\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }		                       	  
		              state = 2;
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    		state = 2;
		    	}
            }else if(command.equals("OFF")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   //Desactivarlo
		            		   dataWriter.writeBytes("204 OK Sensor deactivated\r\n");
		            	   }
		            	   else dataWriter.writeBytes("419 ERR Sensor already deactivated\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }		                       	  
		              state = 2;
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    		state = 2;
		    	}
            }else if(command.equals("GPSON")){
            	
            	if (true/*Esta Activado?*/){
         		   //Activarlo
         		   dataWriter.writeBytes("205 OK GPS activated\r\n");
            	}
         	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
            	state = 2;
            	
            }else if(command.equals("GPSOFF")){
            	
            	if (true/*Esta Activado?*/){
         		   //Desactivarlo
         		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
            	}
         	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
            	state = 2;
            	
            }else if(command.equals("GET_CURVALUE")){
            	try{
		              pass = sTok.nextToken();
		              
		              //Añadir la condicion		              
		              if (true/*existe el sensor?*/){
		            	   if (true/*Esta Activado?*/){
		            		   
		            		   dataWriter.writeBytes("114 OK ");
		            		   dataWriter.writeBytes(null/*Devolver los datos actuales del sensor en una linea (crear un metodo en la clase)*/);
		            		   dataWriter.writeBytes("\r\n");
		            	   }
		            	   else dataWriter.writeBytes("416 ERR Sensor is not active\r\n");
		              }		         
		              else{
		            	  dataWriter.writeBytes("414 ERR Unknown sensor\r\n");
		              }		                       	  
		              state = 2;
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    		state = 2;
		    	}
          }else if(command.equals("GET_PIC")){
          	
          	if (true/*Esta Activado el GPS?*/){
          		dataWriter.writeBytes("207 OK Transmitting ");
          		dataWriter.writeBytes(null/*Mostrar bytes de la foto*/ + " bytes");
          		//Enviar foto
          		dataWriter.writeBytes(null/*Mostrar bytes de la foto*/ + " bytes transmitted");
          	}
       	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
          	state = 2;
          	
          }else if(command.equals("QUIT")){
              state = 4;
            }else{
              dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
         break; 
        	 
        }
      }
    dataWriter.writeBytes("208 OK Bye\r\n");
    dataWriter.close();
    dataReader.close();
    socket.close();
    }catch(IOException ioe){
      System.err.println(ioe);
    }catch(NoSuchElementException nsee){
      System.err.println(nsee);
    }
  }
}
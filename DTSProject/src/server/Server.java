
package server;

import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import server.domainClasses.Sensor;
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
  private String sensorID = null;
  private Sensor sen = null;
  private String vehicleID = null;
  private String userID = null;
  private String pass = null;
  private ServerController controller = null;
  




  /**
  * Server constructor, which serves to the client.
  * @param d Tabla de correspondencias de diciconario a utilizar.
  * @param sc Socket que comunica con el cliente conectado.
  */
  public Server( String vehicleCode, Socket sc , ServerController controller) {
    try{
      vehicleDAO.connect();
      vehicleID = vehicleCode;
      vehicleData = vehicleDAO.getVehicleInformation();
      vehicleData.setID_vehicle(vehicleID);
      vehicleDAO.disconnect();
      socket = sc;
      dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      dataWriter = new DataOutputStream(socket.getOutputStream());
      this.controller = controller;
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
      
      
      int state = 0;
      
      for (;state<4;){
        line = dataReader.readLine();
        if (line==null) return;
        System.out.println("Recieved line: " + line + " from " + socket.getInetAddress().getHostAddress());
        StringTokenizer sTok= new StringTokenizer(line," ");
        command = sTok.nextToken().toUpperCase();

        switch(state){
          case 0:
        	  //--------------------------USER [USER]-----------------------------
        	if(command.equals("USER")){
        		try{
		              userID = sTok.nextToken();
		              dataWriter.writeBytes("201 OK Welcome " + userID + "\r\n");
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
        	//--------------------------PASS [PASSWORD]-----------------------------
            if(command.equals("PASS")){
            	try{
		              pass = sTok.nextToken();
		              userDAO.connect();
		              
		              System.out.println(userID);
		              
		              if(pass.equals(userDAO.getUserPassword(userID))){
		            	  
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
		    		dataWriter.writeBytes("402 ERR Authentication error\r\n");
	                state = 0;
					e.printStackTrace();
				}
            }//--------------------------QUIT-----------------------------
            else if(command.equals("QUIT")){
              state = 4;
            }else{
              dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
		    break;    

          case 2:
        	//--------------------------LISTSENSOR-----------------------------
            if(command.equals("LISTSENSOR")){
            	dataWriter.writeBytes("112 OK Start of sensor list\r\n");
            	
            	List<String> list = vehicleData.convertToListSensor();
          	  	for(int i=0;i<list.size();i++)
          	  		dataWriter.writeBytes(list.get(i) + "\r\n");
          	  
              
              dataWriter.writeBytes("212 OK End of sensor list\r\n");
              
            }//--------------------------HISTORYLOG-----------------------------
            else if(command.equals("HISTORYLOG")){
            	try{
		              sensorID = sTok.nextToken();		                       
		              
		              if (vehicleData.HasSensor(sensorID)){
		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n");
		            	  
		            	
		            	List<String> list = vehicleData.getSensor(sensorID).convertLogsToList();
		              	for(int i=0;i<list.size();i++)		              	
		            	  		dataWriter.writeBytes(list.get(i) + "\r\n");
		            	  
		            	  dataWriter.writeBytes("213 OK End of measurement list\r\n");	  
		              }
		              else{
		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
		              }  		             
            	}
		    	catch(NoSuchElementException e){
		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
		    	}
	              
            }//--------------------------ON [SENSOR]-----------------------------
            else if(command.equals("ON")){
            	try{
		              sensorID = sTok.nextToken();
		              
		              sen = vehicleData.getSensor(sensorID);	              
		              if (vehicleData.HasSensor(sensorID)){
		            	   if (!sen.isActivated()){
		            		   
		            		   sen.setState("ON");
		            		   try {
		            		    	vehicleDAO.connect();
		            			  	vehicleDAO.setSensorState(sensorID, "ON");
		            			   	vehicleDAO.disconnect();									
		            		   } catch (SQLException e) {
									e.printStackTrace();
								}
		            		   
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
            }//--------------------------OFF [SENSOR]-----------------------------
            else if(command.equals("OFF")){
            	try{
		              sensorID = sTok.nextToken();
		              
		              sen = vehicleData.getSensor(sensorID);	              
		              if (vehicleData.HasSensor(sensorID)){
		            	   if (sen.isActivated()){
		            		   
		            		   sen.setState("OFF");
		            		   try {
		            			   vehicleDAO.connect();
		            			   vehicleDAO.setSensorState(sensorID, "OFF");
		            			   vehicleDAO.disconnect();
		            		    } catch (SQLException e) {
								   	e.printStackTrace();
								}
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
            }//--------------------------GPSON-----------------------------
            else if(command.equals("GPSON")){
            	
            	if (!vehicleData.isGPSActivated()){
            		
            		vehicleData.setState("ON");
            		try {
           			   vehicleDAO.connect();
          			   vehicleDAO.setGPSState("ON");
          			   vehicleDAO.disconnect();
           		    } catch (SQLException e) {
 						   	e.printStackTrace();
 					}
         		   
         		   dataWriter.writeBytes("205 OK GPS activated\r\n");
            	}
         	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
            	
            }//--------------------------GPSOFF-----------------------------
            else if(command.equals("GPSOFF")){
            	
            	if (vehicleData.isGPSActivated()){
            		
            		vehicleData.setState("OFF");
            		try {
           			   vehicleDAO.connect();
          			   vehicleDAO.setGPSState("OFF");
          			   vehicleDAO.disconnect();
           		    } catch (SQLException e) {
 						   	e.printStackTrace();
 					}
            		
         		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
            	}
         	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
            	
            }//--------------------------GET_CURVALUE [SENSOR]-----------------------------
            else if(command.equals("GET_CURVALUE")){
            	try{
		              pass = sTok.nextToken();
		              
		              sen = vehicleData.getSensor(sensorID);	              
		              if (vehicleData.HasSensor(sensorID)){
		            	  if (sen.isActivated()){
		            		   
		            		   dataWriter.writeBytes("114 OK ");
		            		   dataWriter.writeBytes(sen.GetCurrentValue());
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
          }//--------------------------GET_PIC-----------------------------
            else if(command.equals("GET_PIC")){
          	
          	if (vehicleData.isGPSActivated()){
          	             	    
          	    try {          	    	
          	    	 File fich = new File("files\\Highway.jpg");

                     FileInputStream fis = new FileInputStream(fich);
                     long fileSize = fich.length();
                     byte[] buffer = new byte[(int)fileSize];		
                     dataWriter.writeBytes("207 OK Transmitting " + buffer.length + " bytes....\r\n");                    		
                     dataWriter.writeBytes(fileSize+"\r\n");		
                     fis.read(buffer);									
                     this.socket.getOutputStream().write(buffer);
                     dataWriter.writeBytes(fileSize + " bytes transmitted.\r\n");
                     fis.close();
          	    }
          	    catch (FileNotFoundException e) {
          	      System.out.println("File Could not be opened");
          	    } catch (Exception e) {
					e.printStackTrace();
				}
          	    state = 3;
          		
           	}
       	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
          	
          } //--------------------------GET_LOC-----------------------------
            else if(command.equals("GET_LOC")){ 
        	  dataWriter.writeBytes("501 ERR This command is only allowed after getting a picture\r\n");
          }else if(command.equals("QUIT")){
              state = 4;
            }else{
              dataWriter.writeBytes("500 ERR Incorrect command\r\n");
            }
         break;
            
         case 3:
        	 
        	 //All the commands accepted in the state 3 are accepted in this state too.
        	 //The difference is that command GET_LOC it could be executed here.
        	 //When any command is requested (except QUIT), the proccess will return to the state 2.
        	 
        	//--------------------------LISTSENSOR-----------------------------
        	 if(command.equals("LISTSENSOR")){
             	dataWriter.writeBytes("112 OK Start of sensor list\r\n");
             	
             	List<String> list = vehicleData.convertToListSensor();             	
           	  	for(int i=0;i<list.size();i++)
           	  		dataWriter.writeBytes(list.get(i) + "\r\n");
           	  
               
                dataWriter.writeBytes("212 OK End of sensor list\r\n");
                state = 2;
               
             }//--------------------------HISTORYLOG-----------------------------
             else if(command.equals("HISTORYLOG")){
             	try{
 		              sensorID = sTok.nextToken();		                       
 		              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n"); 		            	  
 		            	
 		            	 List<String> list = vehicleData.getSensor(sensorID).convertLogsToList();
 		              	 for(int i=0;i<list.size();i++)		              	
 		            	  		dataWriter.writeBytes(list.get(i) + "\r\n");
 		            	  
 		            	  dataWriter.writeBytes("212 OK End of measurement list\r\n");	  
 		              }
 		              else{
 		            	  dataWriter.writeBytes("417 ERR Unknown sensor\r\n");
 		              } 
 		      	}
 		    	catch(NoSuchElementException e){
 		    		dataWriter.writeBytes("415 ERR Missing sensor_id parameter\r\n");
 		    	} 		    	
 		    	state = 2;   
 		    	
             }//--------------------------ON [SENSOR]-----------------------------
             else if(command.equals("ON")){
             	try{
 		              sensorID = sTok.nextToken();
 		              
 		              sen = vehicleData.getSensor(sensorID);	              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	   if (!sen.isActivated()){
 		            		   
 		            		   sen.setState("ON");
 		            		   try {
 									vehicleDAO.connect();
		            			  	vehicleDAO.setSensorState(sensorID, "ON");
		            			   	vehicleDAO.disconnect();	
 		            		   } catch (SQLException e) {
 									e.printStackTrace();
 								}
 		            		   
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
 		    	state = 2;
 		    	
             }//--------------------------OFF [SENSOR]-----------------------------
             else if(command.equals("OFF")){
             	try{
 		              sensorID = sTok.nextToken();
 		              
 		              sen = vehicleData.getSensor(sensorID);	              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	   if (sen.isActivated()){
 		            		   
 		            		   sen.setState("OFF");
 		            		   try {
 		            			   vehicleDAO.connect();
		            			   vehicleDAO.setSensorState(sensorID, "OFF");
		            			   vehicleDAO.disconnect();
 		            		    } catch (SQLException e) {
 								   	e.printStackTrace();
 								}
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
 		    	state = 2;
 		    	
             }//--------------------------GPSON-----------------------------
             else if(command.equals("GPSON")){
             	
             	if (!vehicleData.isGPSActivated()){
             		
             		vehicleData.setState("ON");
             		try {
          			   vehicleDAO.connect();
         			   vehicleDAO.setGPSState("ON");
         			   vehicleDAO.disconnect();
          		    } catch (SQLException e) {
						   	e.printStackTrace();
					}
          		   dataWriter.writeBytes("205 OK GPS activated\r\n");
             	}
          	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
             	state = 2;
             	
             }//--------------------------GPSOFF-----------------------------
             else if(command.equals("GPSOFF")){
             	
             	if (vehicleData.isGPSActivated()){
             		
             		vehicleData.setState("OFF");
             		try {
           			   vehicleDAO.connect();
          			   vehicleDAO.setGPSState("OFF");
          			   vehicleDAO.disconnect();
           		    } catch (SQLException e) {
 						   	e.printStackTrace();
 					}
          		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
             	}
          	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
             	state = 2;
             	
             }//--------------------------GET_CURVALUE [SENSOR]-----------------------------
             else if(command.equals("GET_CURVALUE")){
             	try{
 		              pass = sTok.nextToken();
 		              
 		              sen = vehicleData.getSensor(sensorID);	              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	  if (sen.isActivated()){
 		            		   
 		            		   dataWriter.writeBytes("114 OK ");
 		            		   dataWriter.writeBytes(sen.GetCurrentValue());
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
 		    	state = 2;
 		    	
           }//--------------------------GET_PIC-----------------------------
             else if(command.equals("GET_PIC")){
           	
           		if (vehicleData.isGPSActivated()){
           	             	    
           	    try {
           	    	File fich = new File("files\\Highway.jpg");

                    FileInputStream fis = new FileInputStream(fich);
                    long fileSize = fich.length();
                    byte[] buffer = new byte[(int)fileSize];		
                    dataWriter.writeBytes("207 OK Transmitting " + buffer.length + " bytes....\r\n");                    		
                    dataWriter.writeBytes(fileSize+"\r\n");		
                    fis.read(buffer);									
                    this.socket.getOutputStream().write(buffer);
                    dataWriter.writeBytes(fileSize + " bytes transmitted.\r\n");
                    fis.close();                   
           	      
           	    }
           	    catch (FileNotFoundException e) {
           	      System.out.println("File Could not be opened");
           	    } catch (Exception e) {
 					e.printStackTrace();
 				}
           		
            	}
        	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
           		state = 2;
           		
           }//--------------------------GET_LOC-----------------------------
             else if(command.equals("GET_LOC")){ 
        	   if (vehicleData.isGPSActivated()){
            		dataWriter.writeBytes("115 OK ");            		
            		dataWriter.writeBytes(vehicleData.getLocation() + "\r\n");
            	}
         	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
            	state = 2;
           }//--------------------------QUIT-----------------------------
             else if(command.equals("QUIT")){
               state = 4;
             }else{
               dataWriter.writeBytes("500 ERR Incorrect command\r\n");
             }
          break;
        	 
        }
      }
    dataWriter.writeBytes("208 OK Bye\r\n");
    closeServer();
    }catch(IOException ioe){
      System.err.println(ioe);
    }catch(NoSuchElementException nsee){
      System.err.println(nsee);
    }
  }
  
  public void closeServer() throws IOException{
	  dataWriter.close();
	  dataReader.close();
	  socket.close();
	  controller.removeServer(this);

  }
  
  private void sendBytes(FileInputStream fis, int fileLenght) throws Exception {
	    // Construct a buffer to hold bytes on their way to the socket.
	    byte[] buffer = new byte[fileLenght];
	    this.socket.getOutputStream().write(buffer);
	
  }
  
  public String getUserID() {
		return userID;
  }
		
  public String getPass() {
		return pass;
  }
}
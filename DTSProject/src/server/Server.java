
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

  /**
  * Server constructor, which serves to the client.
  * @param d Tabla de correspondencias de diciconario a utilizar.
  * @param sc Socket que comunica con el cliente conectado.
  */
  public Server( String vehicleCode, Socket sc ) {
    try{
      vehicleDAO.connect();
      vehicleID = vehicleCode;
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
            	
            	List<String> list = vehicleData.convertToListSensor();
            	dataWriter.writeBytes(list.size() + "\r\n");
          	  	for(int i=0;i<list.size();i++)
          	  		dataWriter.writeBytes(vehicleData.getID_vehicle() + "; " + list.get(i) + "\r\n");
          	  
              
              dataWriter.writeBytes("212 OK End of sensor list\r\n");
              
            }else if(command.equals("HISTORYLOG")){
            	try{
		              sensorID = sTok.nextToken();		                       
		              
		              if (vehicleData.HasSensor(sensorID)){
		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n");
		            	  
		            	
		            	List<Sensor> list = vehicleData.getSensors();
		              	dataWriter.writeBytes(list.size() + "\r\n");
		              	for(int i=0;i<list.size();i++)
		            	  		dataWriter.writeBytes(vehicleData.getID_vehicle() + "; " + list.get(i).convertLogsToList() + "\r\n");
		            	  
		            	  
		            	  
		            	  dataWriter.writeBytes("213 OK End of measurement list\r\n");	  
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
		              sensorID = sTok.nextToken();
		              
		              sen = vehicleData.getSensor(sensorID);	              
		              if (vehicleData.HasSensor(sensorID)){
		            	   if (!sen.isActivated()){
		            		   
		            		   sen.setState("ON");
		            		   /*try {
									vehicleDAO.setSensorState(sensorID, "ON");
		            		   } catch (SQLException e) {
									e.printStackTrace();
								}*/
		            		   
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
		              sensorID = sTok.nextToken();
		              
		              sen = vehicleData.getSensor(sensorID);	              
		              if (vehicleData.HasSensor(sensorID)){
		            	   if (sen.isActivated()){
		            		   
		            		   sen.setState("OFF");
		            		   /*try {
		            			   vehicleDAO.setSensorState(sensorID, "OFF");
		            		    } catch (SQLException e) {
								   	e.printStackTrace();
								}*/
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
            	
            	if (!vehicleData.isGPSActivated()){
            		
            		vehicleData.setState("ON");
            		/*try {
						vehicleDAO.setGPSState(vehicleID, "ON");
					} catch (SQLException e) {
						e.printStackTrace();
					}*/
         		   
         		   dataWriter.writeBytes("205 OK GPS activated\r\n");
            	}
         	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
            	
            }else if(command.equals("GPSOFF")){
            	
            	if (vehicleData.isGPSActivated()){
            		
            		/*vehicleData.setState("OFF");
            		try {
						vehicleDAO.setGPSState(vehicleID, "OFF");
					} catch (SQLException e) {
						e.printStackTrace();
					}*/
            		
         		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
            	}
         	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
            	
            }else if(command.equals("GET_CURVALUE")){
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
          }else if(command.equals("GET_PIC")){
          	
          	if (vehicleData.isGPSActivated()){
          		//Hay que hacer dinamico el nombre del fichero?
          		String fileName = "files/Highway.jpg" ;
          		FileInputStream fis = null;
          	             	    
          	    try {
          	      fis = new FileInputStream(fileName);
          	                	            	        	      
          	      dataWriter.writeBytes("207 OK Transmitting....\r\n");
          	      int bytes = sendBytes(fis); 
          	      dataWriter.writeBytes("\r\n");
          	      dataWriter.writeBytes(bytes + " bytes transmitted\r\n");
          	      
          	    }
          	    catch (FileNotFoundException e) {
          	      System.out.println("File Could not be opened");
          	    } catch (Exception e) {
					e.printStackTrace();
				}
          	    state = 3;
          		
           	}
       	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
          	
          }else if(command.equals("GET_LOC")){ 
        	  dataWriter.writeBytes("501 ERR This command is only allowed after using GET_PIC command\r\n");
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
        	 
        	 if(command.equals("LISTSENSOR")){
             	dataWriter.writeBytes("112 OK Start of sensor list\r\n");
             	
             	List<String> list = vehicleData.convertToListSensor();
             	dataWriter.writeBytes(list.size() + "\r\n");
           	  	for(int i=0;i<list.size();i++)
           	  		dataWriter.writeBytes(vehicleData.getID_vehicle() + "; " + list.get(i) + "\r\n");
           	  
               
                dataWriter.writeBytes("212 OK End of sensor list\r\n");
                state = 2;
               
             }else if(command.equals("HISTORYLOG")){
             	try{
 		              sensorID = sTok.nextToken();		                       
 		              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	  dataWriter.writeBytes("113 OK Start of measurement list\r\n");
 		            	  
 		            	
 		            	List<Sensor> list = vehicleData.getSensors();
 		              	dataWriter.writeBytes(list.size() + "\r\n");
 		              	for(int i=0;i<list.size();i++)
 		            	  		dataWriter.writeBytes(vehicleData.getID_vehicle() + "; " + list.get(i).convertLogsToList() + "\r\n");
 		            	  
 		            	  
 		            	  
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
 		    	
             }else if(command.equals("ON")){
             	try{
 		              sensorID = sTok.nextToken();
 		              
 		              sen = vehicleData.getSensor(sensorID);	              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	   if (!sen.isActivated()){
 		            		   
 		            		   sen.setState("ON");
 		            		   /*try {
 									vehicleDAO.setSensorState(sensorID, "ON");
 		            		   } catch (SQLException e) {
 									e.printStackTrace();
 								}*/
 		            		   
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
 		    	
             }else if(command.equals("OFF")){
             	try{
 		              sensorID = sTok.nextToken();
 		              
 		              sen = vehicleData.getSensor(sensorID);	              
 		              if (vehicleData.HasSensor(sensorID)){
 		            	   if (sen.isActivated()){
 		            		   
 		            		   sen.setState("OFF");
 		            		   /*try {
 		            			   vehicleDAO.setSensorState(sensorID, "OFF");
 		            		    } catch (SQLException e) {
 								   	e.printStackTrace();
 								}*/
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
 		    	
             }else if(command.equals("GPSON")){
             	
             	if (!vehicleData.isGPSActivated()){
             		
             		vehicleData.setState("ON");
             		/*try {
 						vehicleDAO.setGPSState(vehicleID, "ON");
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}*/
          		   
          		   dataWriter.writeBytes("205 OK GPS activated\r\n");
             	}
          	   	else dataWriter.writeBytes("419 ERR GPS already activated\r\n");
             	state = 2;
             	
             }else if(command.equals("GPSOFF")){
             	
             	if (vehicleData.isGPSActivated()){
             		
             		/*vehicleData.setState("OFF");
             		try {
 						vehicleDAO.setGPSState(vehicleID, "OFF");
 					} catch (SQLException e) {
 						e.printStackTrace();
 					}*/
             		
          		   dataWriter.writeBytes("206 OK GPS deactivated\r\n");
             	}
          	   	else dataWriter.writeBytes("420 ERR GPS already deactivated\r\n");
             	state = 2;
             	
             }else if(command.equals("GET_CURVALUE")){
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
 		    	
           }else if(command.equals("GET_PIC")){
           	
           		if (vehicleData.isGPSActivated()){
           		//Hay que hacer dinamico el nombre del fichero?
           		String fileName = "files/Highway.jpg" ;
           		FileInputStream fis = null;
           	             	    
           	    try {
           	      fis = new FileInputStream(fileName);
           	                	            	        	      
           	      dataWriter.writeBytes("207 OK Transmitting....\r\n");
           	      int bytes = sendBytes(fis); 
           	      dataWriter.writeBytes("\r\n");
           	      dataWriter.writeBytes(bytes + " bytes transmitted\r\n");
           	      
           	    }
           	    catch (FileNotFoundException e) {
           	      System.out.println("File Could not be opened");
           	    } catch (Exception e) {
 					e.printStackTrace();
 				}
           		
            	}
        	   	else dataWriter.writeBytes("421 ERR GPS is not active\r\n");
           		state = 2;
           		
           }else if(command.equals("GET_LOC")){ 
        	   if (vehicleData.isGPSActivated()){
            		dataWriter.writeBytes("115 OK ");            		
            		dataWriter.writeBytes(vehicleData.getLocation() + "\r\n");
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
  
  private int sendBytes(FileInputStream fis) throws Exception {
	    // Construct a 1K buffer to hold bytes on their way to the socket.
	    byte[] buffer = new byte[1024];
	    int bytes = 0;
	    int numBytes = 0;

	    // Copy requested file into the socket's output stream.
	    while ( (bytes = fis.read(buffer)) != -1) {
	    	dataWriter.write(buffer, 0, bytes);
	    	numBytes+=bytes;
	    }
		return numBytes;
	  }
  
}
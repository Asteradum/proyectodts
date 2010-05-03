package client;


import java.net.*;
import java.io.*;
import java.util.*;

import data.*;

/**
* La clase DiccionarioClient contiene la implementación del cliente
* con los métodos entrar(), lista(), anadir() y salir().
*
*/
public class ClientController {

/** Socket por el que se realiza la comunicación. */
private Socket socket = null;

/** Filtro de recogida de datos del socket. */
private BufferedReader dataReader = null;

/** Filtro de escritura de datos al socket. */
private DataOutputStream dataWriter = null;

/**
* Constructor de DiccionarioClient que realiza la conexión con la dirección IP suministrada.
* @param IP Dirección IP del servidor de diccionario al que conectar.
*/
public ClientController(String IP) {
 try{
     socket = new Socket(IP,DataConnection.PORT);
     dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     dataWriter = new DataOutputStream(socket.getOutputStream());
 }catch(IOException ioe){
   System.err.println(ioe);
 }
}

	public boolean login(String user, String pass){
	 try{
		 	dataWriter.writeBytes("USER " + user + "\r\n");
			System.out.println(dataReader.readLine()); 
			
			dataWriter.writeBytes("PASS " + pass + "\r\n");		 
		    String r = dataReader.readLine(); 
		    System.out.println(r);
		    
		   if (r.startsWith("202"))
		     return true;
		   else
		     return false;
	 }catch(IOException ioe){
	   System.err.println(ioe);
	 }
	 return false;
	}
	
	public List<String> getListSensors(){
		try {
			List<String> sensors = new ArrayList<String>();
			String r = null;
			
			dataWriter.writeBytes("LISTSENSOR\r\n");
			r = dataReader.readLine();
			System.out.println(r);
			if (r.startsWith("112")){
				r = dataReader.readLine();
				while (!r.startsWith("212"))
					sensors.add(dataReader.readLine());
				
				
				//Si funciona borrar este for y el mensaje del numero de sensores previo del server.
				for(int i=0;i<sensors.size();i++)
					System.out.println(sensors.get(i));
				
				return sensors;
			}
			else return null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	public List<String> getHistoryLog(String sensor){		
		try {
			List<String> logs = new ArrayList<String>();
			String r = null;
			
			dataWriter.writeBytes("HISTORYLOG " + sensor + "\r\n");
			r =dataReader.readLine();
			
			
			if (r.startsWith("113")){
				r = dataReader.readLine();
				while (!r.startsWith("213"))
					logs.add(dataReader.readLine());
							
				//Si funciona borrar este for y el mensaje del numero de log previo del server.
				for(int i=0;i<logs.size();i++)
					System.out.println(logs.get(i));
				
				return logs;
			}
			else return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Todos los metodos de ON/OFF, devolver en vez de un booleano, 
	//si ha sido correcto/no correcto/ya estaba en ese estado
	public boolean setSensorOFF (String sensor){
		
		try {
			dataWriter.writeBytes("OFF " + sensor + "\r\n");
			String r = dataReader.readLine();
			System.out.println(r);
			
			if (r.startsWith("204"))
				return true;
			else return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setSensorON (String sensor){
		
		try {
			dataWriter.writeBytes("ON " + sensor + "\r\n");
			String r = dataReader.readLine();
			System.out.println(r);
			
			if (r.startsWith("203"))
				return true;
			else return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setGPSOFF (){
		try {
			dataWriter.writeBytes("GPSOFF\r\n");
			String r = dataReader.readLine();
			System.out.println(r);
			
			if (r.startsWith("206"))
				return true;
			else return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setGPSON (){
		try {
			dataWriter.writeBytes("GPSON\r\n");
			String r = dataReader.readLine();
			System.out.println(r);
			
			if (r.startsWith("205"))
				return true;
			else return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getCurrentValue(String sensor){
		StringTokenizer sTok= null;
		String r = null;
		try {
			dataWriter.writeBytes("GET_CURVALUE " + sensor + "\r\n");
			r = dataReader.readLine();
			System.out.println(r);
			if (r.startsWith("114")){
				sTok= new StringTokenizer(r," ");
				sTok.nextToken();sTok.nextToken();
				r = sTok.nextToken(); 
				return r;
			}
			else return "Error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error";
	}
	
	public String getLocation(){
		
		StringTokenizer sTok= null;
		String r = null;
		try {
			dataWriter.writeBytes("GET_LOC\r\n");
			r = dataReader.readLine();
			System.out.println(r);
			if (r.startsWith("115")){
				sTok= new StringTokenizer(r," ");
				sTok.nextToken();sTok.nextToken();
				r = sTok.nextToken(); 
				return r;
			}
			else return r;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error";
	}
	
	
	//Tiene que devovler la ruta donde ha dejado guardada la foto
	//En un futuro peude que se cambie por un dato de devolucion tipo Image
	//¿Utilizar una Excepcion nueva?
	public String getPicture(){
		String r = null;
		try {
			dataWriter.writeBytes("GET_PIC\r\n");
			r = dataReader.readLine();
			System.out.println(r);
			if (r.startsWith("207")){
				String photoData = null;
				String output = dataReader.readLine();
				while (!output.endsWith("bytes transmitted")){
					photoData = output;
					output = dataReader.readLine();
				}
				System.out.println(output);
				return photoData;
			}
			else return r;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
		
	}


	public void salir(){
		 try{
		   dataWriter.writeBytes("QUIT\r\n");
		   System.out.println(dataReader.readLine());
		   dataWriter.close();
		   dataReader.close();
		   socket.close();
		 }catch(IOException ioe){
		   System.err.println(ioe);
		 }
		}
}
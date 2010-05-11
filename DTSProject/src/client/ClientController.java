package client;


import java.net.*;
import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

import client.ServerException.ServerException;
import client.gui.ClientGUI;

import data.*;

public class ClientController {


	private Socket socket = null;
	private DataOutputStream dataWriter = null;
	private BufferedReader dataReader = null;
	private ClientGUI form = null;


	public ClientController()  {
		form = new ClientGUI(this);
	}

	public boolean connect (String IP){
		try {
			 socket = new Socket(IP,DataConnection.PORT);
		     dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     dataWriter = new DataOutputStream(socket.getOutputStream());
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void login(String user, String pass) throws ServerException{
	 try{
		 	String r = null;
		 	dataWriter.writeBytes("USER " + user + "\r\n");
			r = dataReader.readLine(); 
			
			dataWriter.writeBytes("PASS " + pass + "\r\n");		 
		    r = dataReader.readLine(); 
		    
		    
			throw new ServerException(r);
	 }catch(IOException ioe){
	   System.err.println(ioe);
	 }
	}
	
	public List<String> getListSensors() throws ServerException{
		try {
			List<String> sensors = new ArrayList<String>();
			String r = null;
			
			dataWriter.writeBytes("LISTSENSOR\r\n");
			r = dataReader.readLine();
			if (r.startsWith("112")){
				r = dataReader.readLine();
				while (!r.startsWith("212")){					
					sensors.add(r);
					r = dataReader.readLine();
				}
				return sensors;
			}
			else throw new ServerException(r);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	public List<String> getHistoryLog(String sensor) throws ServerException{		
		try {
			List<String> logs = new ArrayList<String>();
			String r = null;
			
			dataWriter.writeBytes("HISTORYLOG " + sensor + "\r\n");
			r =dataReader.readLine();
			if (r.startsWith("113")){
				r = dataReader.readLine();
				while (!r.startsWith("213")){
					logs.add(r);
					r = dataReader.readLine();
				}			
				
				return logs;
			}
			else throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setSensorOFF (String sensor) throws ServerException{
		
		try {
			dataWriter.writeBytes("OFF " + sensor + "\r\n");
			String r = dataReader.readLine();

			throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSensorON (String sensor) throws ServerException{
		
		try {
			dataWriter.writeBytes("ON " + sensor + "\r\n");
			String r = dataReader.readLine();
			
			throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setGPSOFF () throws ServerException{
		try {
			dataWriter.writeBytes("GPSOFF\r\n");
			String r = dataReader.readLine();
			
			throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setGPSON () throws ServerException{
		try {
			dataWriter.writeBytes("GPSON\r\n");
			String r = dataReader.readLine();
			
			throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentValue(String sensor) throws ServerException{
		StringTokenizer sTok= null;
		String r = null;
		try {
			dataWriter.writeBytes("GET_CURVALUE " + sensor + "\r\n");
			r = dataReader.readLine();
			if (r.startsWith("114")){
				sTok= new StringTokenizer(r," ");
				sTok.nextToken();sTok.nextToken();
				r = sTok.nextToken();
				r = r + ";" + sTok.nextToken();
				
				return r;
			}
			else throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLocation() throws ServerException{
		
		StringTokenizer sTok= null;
		String r = null;
		try {
			dataWriter.writeBytes("GET_LOC\r\n");
			r = dataReader.readLine();
			if (r.startsWith("115")){
				sTok= new StringTokenizer(r," ");
				sTok.nextToken();sTok.nextToken();
				r = sTok.nextToken(); 
				return r;
			}
			else throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void getPicture() throws ServerException{
		String r = null;
		
		try {
			File fileName = new File("recievedData\\Photo.jpg") ;
			FileOutputStream fos = new FileOutputStream(fileName);			
			dataWriter.writeBytes("GET_PIC\r\n");
			r = dataReader.readLine();
			System.out.println(r);
			if (r.startsWith("207")){
				int lenght = Integer.parseInt(dataReader.readLine());
				byte[] buffer = new byte[lenght];		   
		    	int bytesRead =0;		    	
		    	/*while (bytesRead<lenght){
		    		bytesRead += socket.getInputStream().read(buffer, bytesRead, ((lenght-bytesRead)>=5000)?5000:lenght-bytesRead+1);
		    	}*/
		    	socket.getInputStream().read(buffer,0, lenght);
				fos.write(buffer);
				r = dataReader.readLine();
				while (!r.endsWith("bytes transmitted."))
					r = dataReader.readLine();
				r = lenght + " bytes transmitted.";
				fos.close();
				throw new ServerException(r);
			}
			else throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getGPSState() throws ServerException{
		StringTokenizer sTok= null;
		String r = null;
		try {
			dataWriter.writeBytes("GPSSTATE\r\n");
			r = dataReader.readLine();
			if (r.startsWith("114")){
				r = dataReader.readLine();
				return r;
			}
			else throw new ServerException(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}


	public void quit(){
		 try{
		   dataWriter.writeBytes("QUIT\r\n");
		   dataReader.readLine();
		   dataWriter.close();
		   dataReader.close();
		   socket.close();
		 }catch(IOException ioe){
		   System.err.println(ioe);
		 }
		}
	
	public static void main(String[] args){
		new ClientController();
	}
	
}
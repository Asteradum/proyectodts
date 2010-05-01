package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import data.DataConnection;

public class PruebaServer {

	private static DataOutputStream dataWriter = null;
	private static BufferedReader dataReader = null;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		Socket socket = new Socket("127.0.0.1",DataConnection.PORT);
		

		dataWriter = new DataOutputStream(socket.getOutputStream());
		dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		dataWriter.writeBytes("USER std1\r\n");
		System.out.println(dataReader.readLine()); 
		
		dataWriter.writeBytes("PASS 1111\r\n");
		System.out.println(dataReader.readLine());
		
		dataWriter.writeBytes("LISTSENSOR\r\n");
		System.out.println(dataReader.readLine());
		int size = Integer.parseInt(dataReader.readLine());
		for(int i=0;i<size;i++)
			System.out.println(dataReader.readLine());
		System.out.println(dataReader.readLine());
		
		dataWriter.writeBytes("HISTORYLOG S1\r\n");
		System.out.println(dataReader.readLine());
		size = Integer.parseInt(dataReader.readLine());
		for(int i=0;i<size;i++)
			System.out.println(dataReader.readLine());
		System.out.println(dataReader.readLine());
		
		socket.close();
	}
}

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
		ServerSocket ss = new ServerSocket(DataConnection.PORT);
		
		Server s = new Server("V1", ss.accept());
		s.start();
		
		Socket socket = new Socket("127.0.0.1",DataConnection.PORT);
		dataWriter = new DataOutputStream(socket.getOutputStream());
		dataReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		dataWriter.writeBytes("stud1");
		System.out.println(dataReader.readLine()); 
		dataWriter.writeBytes("1111");
		String r = dataReader.readLine();
		
	}

}

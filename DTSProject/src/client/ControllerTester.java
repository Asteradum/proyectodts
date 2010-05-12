package client;

import java.rmi.RemoteException;
import java.util.List;

import client.ServerException.ServerException;

public class ControllerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientController client;
		client = new ClientController();		
		client.connect("127.0.0.1");
		
		try {
			client.login("std1", "1111");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		List<String> list = null;
		try {
			list = client.getListSensors();

			for (int i=0; i<list.size(); i++)
				System.out.println(list.get(i));
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			list = client.getHistoryLog("S1");

			for (int i=0; i<list.size(); i++)
				System.out.println(list.get(i));
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setSensorOFF("S1");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setSensorOFF("S1");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setSensorON("S1");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setSensorON("S1");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setGPSOFF();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setGPSOFF();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setGPSON();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.setGPSON();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(client.getCurrentValue("S1"));client.getCurrentValue("S1");
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(client.getLocation());
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			client.getPicture();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(client.getLocation());
		} catch (ServerException e) {
		System.out.println(e.getMessage());
		}

		client.quit();
		
	}

}

package server.vehicleDAO;


import java.sql.SQLException;

import server.userDAO.IUserDAO;
import server.userDAO.UserDAO;




public class Prueba {
	private static IUserDAO DAO;
	
	public static void main(String[] args){
	  DAO=new UserDAO();
	  try {
		DAO.connect();
		String pass=DAO.getUserPassword("std1");
		System.out.println(pass);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	

}
